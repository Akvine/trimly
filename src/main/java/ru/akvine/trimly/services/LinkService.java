package ru.akvine.trimly.services;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.akvine.trimly.enums.LinkType;
import ru.akvine.trimly.exceptions.LimitException;
import ru.akvine.trimly.repositories.LinkRepository;
import ru.akvine.trimly.repositories.entities.ClientEntity;
import ru.akvine.trimly.repositories.entities.LinkEntity;
import ru.akvine.trimly.services.domain.LinkModel;
import ru.akvine.trimly.services.dto.CreateLink;
import ru.akvine.trimly.services.uuid.LinkUuidService;
import ru.akvine.trimly.utils.RandomCodeGenerator;
import ru.akvine.trimly.utils.UUIDGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    @Value("${short.link.domain}")
    private String shortLinkDomain;
    @Value("${short.link.temporary.uuid.length}")
    private int temporaryLinkUuidLength;
    @Value("${short.link.constant.uuid.length}")
    private int constantLinkUuidLength;
    @Value("${short.link.constant.max.counts.per.client}")
    private int maxConstantLinksCount;

    private final ClientService clientService;
    private final LinkUuidService linkUuidService;
    private final LinkRepository linkRepository;

    public LinkModel createLink(CreateLink createLink) {
        Preconditions.checkNotNull(createLink, "createLink is null");

        ClientEntity client = clientService.verifyExistsByUuid(createLink.getClientUuid());
        LinkEntity linkEntityToSave = new LinkEntity()
                .setOriginalLink(createLink.getOriginalLink())
                .setClient(client)
                .setUuid(UUIDGenerator.uuidWithoutDashes());

        if (createLink.getLinkType() == LinkType.TEMPORARY) {
            if (shortLinkDomain.endsWith("/")) {
                linkEntityToSave.setShortLink(shortLinkDomain + RandomCodeGenerator
                        .generateNewRandomCode(temporaryLinkUuidLength));
            } else {
                linkEntityToSave.setShortLink(shortLinkDomain + "/" + RandomCodeGenerator
                        .generateNewRandomCode(temporaryLinkUuidLength));
            }

            linkEntityToSave
                    .setType(LinkType.TEMPORARY)
                    .setExpiresAfter(LocalDateTime.now().plusMinutes(createLink.getExpiresAfterMinutes()));
        } else {
            checkLimitAndThrowException(client);
            if (shortLinkDomain.endsWith("/")) {
                linkEntityToSave.setShortLink(shortLinkDomain + linkUuidService.generate());
            } else {
                linkEntityToSave.setShortLink(shortLinkDomain + "/" + linkUuidService.generate());
            }

            linkEntityToSave
                    .setType(LinkType.CONSTANT)
                    .setExpiresAfter(LocalDateTime.MAX);
        }

        return new LinkModel(linkRepository.save(linkEntityToSave));
    }

    private void checkLimitAndThrowException(ClientEntity client) {
        List<LinkEntity> links = linkRepository
                .findByTypeAndClientUuid(LinkType.CONSTANT.name(), client.getUuid());
        if (links.size() == maxConstantLinksCount) {
            String errorMessage = String.format(
                    "Can't generate short link. Limit = [%s] is reached",
                    maxConstantLinksCount
            );
            throw new LimitException(errorMessage);
        }
    }
}

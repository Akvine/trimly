package ru.akvine.trimly.rest.lk.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.trimly.enums.LinkType;
import ru.akvine.trimly.helper.SecurityHelper;
import ru.akvine.trimly.rest.common.LinkResponse;
import ru.akvine.trimly.rest.lk.dto.links.TrimUrlRequest;
import ru.akvine.trimly.services.dto.CreateLink;

@Component
@RequiredArgsConstructor
public class LinkConverter {
    private final SecurityHelper securityHelper;

    public CreateLink convertToCreateLink(TrimUrlRequest request) {
        return new CreateLink()
                .setOriginalLink(request.getOriginUrl())
                .setClientUuid(securityHelper.getCurrentUser().getUuid())
                .setLinkType(LinkType.CONSTANT);
    }

    public LinkResponse convertToLinkResponse(String shortLink) {
        return new LinkResponse().setShortLink(shortLink);
    }
}

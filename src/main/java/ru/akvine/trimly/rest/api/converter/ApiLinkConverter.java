package ru.akvine.trimly.rest.api.converter;

import org.springframework.stereotype.Component;
import ru.akvine.trimly.enums.LinkType;
import ru.akvine.trimly.rest.api.dto.ApiTrimUrlRequest;
import ru.akvine.trimly.rest.common.LinkResponse;
import ru.akvine.trimly.services.domain.AccessTokenModel;
import ru.akvine.trimly.services.dto.CreateLink;

@Component
public class ApiLinkConverter {
    public CreateLink convertToCreateLink(ApiTrimUrlRequest request, AccessTokenModel accessToken) {
        return new CreateLink()
                .setOriginalLink(request.getOriginUrl())
                .setClientUuid(accessToken.getClient().getUuid())
                .setExpiresAfterMinutes(request.getExpiresAfterMinutes() == null ? null : request.getExpiresAfterMinutes())
                .setLinkType(LinkType.TEMPORARY);
    }

    public LinkResponse convertToLinkResponse(String shortLink) {
        return new LinkResponse().setShortLink(shortLink);
    }
}

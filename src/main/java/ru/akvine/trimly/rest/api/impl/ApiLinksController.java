package ru.akvine.trimly.rest.api.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.trimly.rest.api.converter.ApiLinkConverter;
import ru.akvine.trimly.rest.api.dto.ApiTrimUrlRequest;
import ru.akvine.trimly.rest.api.meta.ApiLinksControllerMeta;
import ru.akvine.trimly.rest.api.validators.ApiLinkValidator;
import ru.akvine.trimly.rest.common.Response;
import ru.akvine.trimly.services.LinkService;
import ru.akvine.trimly.services.auth.ApiAuthenticationService;
import ru.akvine.trimly.services.domain.AccessTokenModel;
import ru.akvine.trimly.services.domain.LinkModel;
import ru.akvine.trimly.services.dto.CreateLink;

@RestController
@RequiredArgsConstructor
public class ApiLinksController implements ApiLinksControllerMeta {
    private final ApiAuthenticationService authenticationService;
    private final ApiLinkConverter apiLinkConverter;
    private final ApiLinkValidator apiLinkValidator;
    private final LinkService linkService;

    @Override
    public Response trim(@RequestHeader("Authorization") String token,
                         @RequestBody @Valid ApiTrimUrlRequest request) {
        AccessTokenModel accessToken = authenticationService.checkToken(token);
        apiLinkValidator.verifyTrimUrlRequest(request);
        CreateLink createLink = apiLinkConverter.convertToCreateLink(request, accessToken);
        LinkModel createdShortLink = linkService.createLink(createLink);
        return apiLinkConverter.convertToLinkResponse(createdShortLink.getShortLink());
    }
}

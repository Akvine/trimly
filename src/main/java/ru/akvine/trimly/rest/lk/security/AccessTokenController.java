package ru.akvine.trimly.rest.lk.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.trimly.rest.common.Response;
import ru.akvine.trimly.rest.lk.converters.AccessTokenConverter;
import ru.akvine.trimly.rest.lk.dto.token.TokenGenerateRequest;
import ru.akvine.trimly.rest.lk.meta.security.AccessTokenControllerMeta;
import ru.akvine.trimly.services.AccessTokenService;
import ru.akvine.trimly.services.domain.AccessTokenModel;
import ru.akvine.trimly.services.dto.token.TokenGenerate;

@RestController
@RequiredArgsConstructor
public class AccessTokenController implements AccessTokenControllerMeta {
    private final AccessTokenService accessTokenService;
    private final AccessTokenConverter accessTokenConverter;

    @Override
    public Response generate(@RequestBody @Valid TokenGenerateRequest request) {
        TokenGenerate tokenGenerate = accessTokenConverter.convertToTokenGenerate(request);
        AccessTokenModel accessToken = accessTokenService.generate(tokenGenerate);
        return accessTokenConverter.convertToTokenGenerateResponse(accessToken);
    }
}

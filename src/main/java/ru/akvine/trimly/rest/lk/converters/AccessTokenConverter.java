package ru.akvine.trimly.rest.lk.converters;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.trimly.helper.SecurityHelper;
import ru.akvine.trimly.rest.lk.dto.token.TokenGenerateRequest;
import ru.akvine.trimly.rest.lk.dto.token.TokenGenerateResponse;
import ru.akvine.trimly.services.domain.AccessTokenModel;
import ru.akvine.trimly.services.dto.token.TokenGenerate;
import ru.akvine.trimly.utils.DateUtils;

@Component
@RequiredArgsConstructor
public class AccessTokenConverter {
    private final SecurityHelper securityHelper;

    public TokenGenerate convertToTokenGenerate(TokenGenerateRequest request) {
        Preconditions.checkNotNull(request, "TokenGenerateRequest is null");
        return new TokenGenerate()
                .setClientUuid(securityHelper.getCurrentUser().getUuid())
                .setExpiredAt(request.getExpiredDate() == null ? null : DateUtils.convertToLocalDateTime(request.getExpiredDate()));
    }

    public TokenGenerateResponse convertToTokenGenerateResponse(AccessTokenModel accessTokenModel) {
        Preconditions.checkNotNull(accessTokenModel, "accessTokenModel is null");
        return new TokenGenerateResponse().setToken(accessTokenModel.getToken());
    }
}

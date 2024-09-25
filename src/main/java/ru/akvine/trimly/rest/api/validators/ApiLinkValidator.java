package ru.akvine.trimly.rest.api.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.akvine.trimly.constants.ApiErrorCodes;
import ru.akvine.trimly.exceptions.ValidationException;
import ru.akvine.trimly.rest.api.dto.ApiTrimUrlRequest;
import ru.akvine.trimly.validators.LinkValidator;

@Component
@RequiredArgsConstructor
public class ApiLinkValidator {
    @Value("${short.link.temporary.lifetime.max.minutes.count}")
    private int lifetimeMaxMinutesCount;

    private final LinkValidator linkValidator;

    public void verifyTrimUrlRequest(ApiTrimUrlRequest request) {
        if (request.getExpiresAfterMinutes() != null &&
                request.getExpiresAfterMinutes() > lifetimeMaxMinutesCount) {
            String errorMessage = String.format(
                    "Lifetime minutes count = [%s] can't be more than maximum lifetime minutes count = [%s]",
                    request.getExpiresAfterMinutes(), lifetimeMaxMinutesCount);
            throw new ValidationException(
                    ApiErrorCodes.Validation.FIELD_INVALID_ERROR,
                    errorMessage
            );
        }

        linkValidator.validate(request.getOriginUrl());
    }
}

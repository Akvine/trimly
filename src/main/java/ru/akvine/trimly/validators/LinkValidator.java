package ru.akvine.trimly.validators;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.akvine.trimly.constants.ApiErrorCodes;
import ru.akvine.trimly.constants.RegExpConstants;
import ru.akvine.trimly.exceptions.ValidationException;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class LinkValidator implements Validator<String> {
    @Value("${server.ssl.enabled}")
    private boolean httpsEnabled;

    @Override
    public void validate(String link) {
        if (StringUtils.isBlank(link)) {
            throw new ValidationException(
                    ApiErrorCodes.Validation.INVALID_URL_ERROR,
                    "URL is blank!"
            );
        }

        try {
            new URI(link);
        } catch (URISyntaxException exception) {
            throw new ValidationException(
                    ApiErrorCodes.Validation.INVALID_URL_ERROR,
                    "URL is blank!"
            );
        }

        if (httpsEnabled) {
            if (!RegExpConstants.httpsUrlPattern.matcher(link).matches()) {
                throw new ValidationException(
                        ApiErrorCodes.Validation.INVALID_URL_ERROR,
                        "Https url is invalid!"
                );
            }
        } else {
            if (!RegExpConstants.httpUrlPattern.matcher(link).matches()) {
                throw new ValidationException(
                        ApiErrorCodes.Validation.INVALID_URL_ERROR,
                        "Https url is invalid!"
                );
            }
        }
    }
}

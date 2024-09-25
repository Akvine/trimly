package ru.akvine.trimly.rest.lk.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.trimly.rest.lk.dto.links.TrimUrlRequest;
import ru.akvine.trimly.validators.LinkValidator;

@Component
@RequiredArgsConstructor
public class LKLinkValidator {
    private final LinkValidator linkValidator;

    public void verifyTrimlyUrlRequest(TrimUrlRequest request) {
        linkValidator.validate(request.getOriginUrl());
    }
}

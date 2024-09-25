package ru.akvine.trimly.services.uuid;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.akvine.trimly.exceptions.LinkUuidGenerationException;
import ru.akvine.trimly.repositories.LinkRepository;

@Service
@RequiredArgsConstructor
public class LinkUuidService {
    private final LinkRepository linkRepository;

    @Value("${short.link.constant.uuid.length}")
    private int length;
    @Value("${short.link.constant.uuid.max.generation.attempts}")
    private int maxAttempts;

    public String generate() {
        for (int i = 0; i < maxAttempts; ++i) {
            String code = RandomStringUtils.randomNumeric(length);
            boolean exists = linkRepository.findByUuid(code).isPresent();

            if (!exists) {
                return code;
            }
        }

        throw new LinkUuidGenerationException("Attempts to generate short link UUID have been exhausted!");
    }
}

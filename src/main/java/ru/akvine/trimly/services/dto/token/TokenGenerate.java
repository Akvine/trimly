package ru.akvine.trimly.services.dto.token;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TokenGenerate {
    private String clientUuid;
    @Nullable
    private LocalDateTime expiredAt;
}

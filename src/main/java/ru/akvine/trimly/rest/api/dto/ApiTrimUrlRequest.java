package ru.akvine.trimly.rest.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiTrimUrlRequest {
    @NotBlank
    private String originUrl;
    private Integer expiresAfterMinutes;
}

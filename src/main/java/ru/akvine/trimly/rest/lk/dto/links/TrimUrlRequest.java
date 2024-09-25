package ru.akvine.trimly.rest.lk.dto.links;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TrimUrlRequest {
    @NotBlank
    private String originUrl;
}

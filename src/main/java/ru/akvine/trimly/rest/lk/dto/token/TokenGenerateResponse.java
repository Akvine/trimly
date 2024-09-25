package ru.akvine.trimly.rest.lk.dto.token;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.trimly.rest.common.SuccessfulResponse;

@Data
@Accessors(chain = true)
public class TokenGenerateResponse extends SuccessfulResponse {
    @NotBlank
    private String token;
}

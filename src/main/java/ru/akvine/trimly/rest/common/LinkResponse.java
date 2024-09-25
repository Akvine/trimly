package ru.akvine.trimly.rest.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LinkResponse extends SuccessfulResponse {
    @NotBlank
    private String shortLink;
}

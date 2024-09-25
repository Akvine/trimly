package ru.akvine.trimly.rest.lk.dto.links;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.trimly.rest.common.SuccessfulResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class ShortLinkResponse extends SuccessfulResponse {
    @NotNull
    private List<@Valid ShortLinkDto> links;
}

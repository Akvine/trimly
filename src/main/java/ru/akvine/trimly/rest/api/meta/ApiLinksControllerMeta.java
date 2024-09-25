package ru.akvine.trimly.rest.api.meta;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.trimly.rest.api.dto.ApiTrimUrlRequest;
import ru.akvine.trimly.rest.common.Response;

@RequestMapping(value = "/api/links")
public interface ApiLinksControllerMeta {
    @PostMapping(value = "/trim")
    Response trim(@RequestHeader("Authorization") String token,
                  @RequestBody @Valid ApiTrimUrlRequest request);
}

package ru.akvine.trimly.rest.lk.meta.security;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.trimly.rest.common.Response;
import ru.akvine.trimly.rest.lk.dto.token.TokenGenerateRequest;

@RequestMapping(value = "/access/tokens")
public interface AccessTokenControllerMeta {
    @PostMapping(value = "/generate")
    Response generate(@RequestBody @Valid TokenGenerateRequest request);
}

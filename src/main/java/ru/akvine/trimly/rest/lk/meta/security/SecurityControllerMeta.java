package ru.akvine.trimly.rest.lk.meta.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.trimly.rest.common.Response;
import ru.akvine.trimly.rest.lk.dto.AuthRequest;

@RequestMapping(value = "/security")
public interface SecurityControllerMeta {
    @PostMapping(value = "/registration")
    Response registration(@RequestBody @Valid AuthRequest request, HttpServletRequest httpServletRequest);

    @PostMapping(value = "/auth")
    Response auth(@RequestBody @Valid AuthRequest request, HttpServletRequest httpServletRequest);

    @PostMapping(value = "/logout")
    Response logout(HttpServletRequest request);
}

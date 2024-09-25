package ru.akvine.trimly.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import ru.akvine.trimly.constants.ApiErrorCodes;
import ru.akvine.trimly.rest.common.ErrorResponse;

import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ErrorResponse AUTH_FAIL_RESPONSE = new ErrorResponse(
            ApiErrorCodes.NO_SESSION,
            "User authentication is required",
            "You need to authenticate."
    );

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println(objectMapper.writeValueAsString(AUTH_FAIL_RESPONSE));
    }
}

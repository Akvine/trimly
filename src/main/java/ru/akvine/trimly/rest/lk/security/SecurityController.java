package ru.akvine.trimly.rest.lk.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.trimly.helper.SecurityHelper;
import ru.akvine.trimly.rest.common.Response;
import ru.akvine.trimly.rest.common.SuccessfulResponse;
import ru.akvine.trimly.rest.lk.dto.AuthRequest;
import ru.akvine.trimly.rest.lk.meta.security.SecurityControllerMeta;
import ru.akvine.trimly.services.ClientService;
import ru.akvine.trimly.services.auth.LKAuthenticationService;
import ru.akvine.trimly.services.domain.ClientModel;

@RestController
@RequiredArgsConstructor
public class SecurityController implements SecurityControllerMeta {
    private final ClientService clientService;
    private final SecurityHelper securityHelper;
    private final LKAuthenticationService lkAuthenticationService;

    @Override
    public Response registration(@RequestBody @Valid AuthRequest request,
                                 HttpServletRequest httpServletRequest) {
        ClientModel clientModel = clientService.create(request.getUsername(), request.getPassword());
        securityHelper.authenticate(clientModel, httpServletRequest);
        return new SuccessfulResponse();
    }

    @Override
    public Response auth(@RequestBody @Valid AuthRequest request,
                         HttpServletRequest httpServletRequest) {
        ClientModel clientModel = lkAuthenticationService.authenticate(
                request.getUsername(),
                request.getPassword());
        securityHelper.authenticate(clientModel, httpServletRequest);
        return new SuccessfulResponse();
    }

    @Override
    public Response logout(HttpServletRequest request) {
        securityHelper.doLogout(request);
        return new SuccessfulResponse();
    }
}

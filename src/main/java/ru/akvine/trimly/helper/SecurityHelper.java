package ru.akvine.trimly.helper;

import com.google.common.base.Preconditions;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.akvine.trimly.config.security.ClientAuthentication;
import ru.akvine.trimly.exceptions.NoSessionException;
import ru.akvine.trimly.services.domain.ClientModel;

@Component
@RequiredArgsConstructor
public class SecurityHelper {
    public void authenticate(ClientModel clientModel, HttpServletRequest request) {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new ClientAuthentication(
                clientModel.getId(),
                clientModel.getUuid(),
                clientModel.getUsername()));

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);
    }

    @Nullable
    public ClientAuthentication getCurrentUserOrNull() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof ClientAuthentication) {
            return (ClientAuthentication) authentication;
        }
        return null;
    }

    public void doLogout(HttpServletRequest request) {
        if (request == null) {
            return;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        ClientAuthentication user = getCurrentUserOrNull();
        if (user == null) {
            return;
        }

        session.removeAttribute("SPRING_SECURITY_CONTEXT");
        SecurityContextHolder.clearContext();
    }

    public ClientAuthentication getCurrentUser() {
        ClientAuthentication user = getCurrentUserOrNull();
        Preconditions.checkNotNull(user, "user is null");
        return user;
    }

    public HttpSession getSession(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            throw new NoSessionException();
        }
        return session;
    }
}

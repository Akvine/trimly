package ru.akvine.trimly.services.auth;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.trimly.exceptions.AuthException;
import ru.akvine.trimly.repositories.AccessTokenRepository;
import ru.akvine.trimly.repositories.entities.AccessTokenEntity;
import ru.akvine.trimly.services.domain.AccessTokenModel;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiAuthenticationService {
    private final AccessTokenRepository accessTokenRepository;

    public AccessTokenModel checkToken(String token) {
        Preconditions.checkNotNull(token, "token is null");
        Optional<AccessTokenEntity> accessTokenOptional = accessTokenRepository.findByToken(token);
        if (accessTokenOptional.isEmpty()) {
            throw new AuthException("Token is invalid");
        }
        return new AccessTokenModel(accessTokenOptional.get());
    }
}

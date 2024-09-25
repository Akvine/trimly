package ru.akvine.trimly.services.auth;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akvine.trimly.repositories.entities.ClientEntity;
import ru.akvine.trimly.services.ClientService;
import ru.akvine.trimly.services.domain.ClientModel;

@Service
@RequiredArgsConstructor
public class LKAuthenticationService {
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    public ClientModel authenticate(String username, String password) {
        Preconditions.checkNotNull(username, "username is null");
        Preconditions.checkNotNull(password, "password is null");

        ClientEntity clientEntity = clientService.verifyExistsByUsername(username);
        if (!passwordEncoder.matches(password, clientEntity.getHash())) {
            throw new BadCredentialsException("Bad credentials!");
        }

        return new ClientModel(clientEntity);
    }
}

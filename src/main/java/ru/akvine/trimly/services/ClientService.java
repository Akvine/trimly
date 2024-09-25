package ru.akvine.trimly.services;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akvine.trimly.exceptions.ClientAlreadyExistsException;
import ru.akvine.trimly.exceptions.ClientNotFoundException;
import ru.akvine.trimly.repositories.ClientRepository;
import ru.akvine.trimly.repositories.entities.ClientEntity;
import ru.akvine.trimly.services.domain.ClientModel;
import ru.akvine.trimly.utils.UUIDGenerator;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientModel create(String username, String password) {
        Preconditions.checkNotNull(username, "username is null");
        Preconditions.checkNotNull(password, "password is null");

        try {
            verifyExistsByUsername(username);
            throw new ClientAlreadyExistsException("Client with username = [" + username + "] already exists!");
        } catch (ClientNotFoundException exception) {
            String hash = passwordEncoder.encode(password);

            ClientEntity client = new ClientEntity()
                    .setUsername(username)
                    .setHash(hash)
                    .setUuid(UUIDGenerator.uuidWithoutDashes());
            return new ClientModel(clientRepository.save(client));
        }
    }

    public ClientEntity verifyExistsByUuid(String uuid) {
        Preconditions.checkNotNull(uuid, "uuid is null");
        return clientRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new ClientNotFoundException("Client not found by uuid = [" + uuid + "]"));
    }

    public ClientEntity verifyExistsByUsername(String username) {
        Preconditions.checkNotNull(username, "username is null");
        return clientRepository
                .findByUuid(username)
                .orElseThrow(() -> new ClientNotFoundException("Client not found by username = [" + username + "]"));
    }
}

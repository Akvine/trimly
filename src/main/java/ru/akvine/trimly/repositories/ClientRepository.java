package ru.akvine.trimly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.akvine.trimly.repositories.entities.ClientEntity;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    @Query("from ClientEntity ce where ce.uuid = :uuid and ce.deleted = false")
    Optional<ClientEntity> findByUuid(@Param("uuid") String uuid);

    @Query("from ClientEntity ce where ce.username = :username and ce.deleted = false")
    Optional<ClientEntity> findByUsername(@Param("username") String username);
}

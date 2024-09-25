package ru.akvine.trimly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.akvine.trimly.repositories.entities.AccessTokenEntity;

import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, Long> {
    @Query("from AccessTokenEntity ate where ate.token = :token")
    Optional<AccessTokenEntity> findByToken(@Param("token") String token);

    @Query("from AccessTokenEntity ate join ate.client atec where atec.id = :clientId")
    Optional<AccessTokenEntity> findByClientId(@Param("clientId") long clientId);
}

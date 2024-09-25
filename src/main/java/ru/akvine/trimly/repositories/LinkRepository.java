package ru.akvine.trimly.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.akvine.trimly.repositories.entities.LinkEntity;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<LinkEntity, Long> {
    @Query("from LinkEntity le where le.uuid = :uuid")
    Optional<LinkEntity> findByUuid(@Param("uuid") String uuid);

    @Query("from LinkEntity le where le.shortLink = :shortLink")
    Optional<LinkEntity> findByShortLink(@Param("shortLink") String shortLink);

    @Query("from LinkEntity le join le.client lec where lec.type = :type and lec.uuid = :clientUuid")
    List<LinkEntity> findByTypeAndClientUuid(@Param("type") String type,
                                             @Param("clientUuid") String clientUuid);
}

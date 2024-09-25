package ru.akvine.trimly.services.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.trimly.repositories.entities.AccessTokenEntity;
import ru.akvine.trimly.services.domain.base.Model;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class AccessTokenModel extends Model {
    private Long id;
    private String token;
    private LocalDateTime expiredAt;
    private ClientModel client;

    public AccessTokenModel(AccessTokenEntity accessTokenEntity) {
        this.id = accessTokenEntity.getId();
        this.token = accessTokenEntity.getToken();
        this.client = new ClientModel(accessTokenEntity.getClient());
        this.expiredAt = accessTokenEntity.getExpiredAt();

        this.createdDate = accessTokenEntity.getCreatedDate();
        this.updatedDate = accessTokenEntity.getUpdatedDate();
    }
}

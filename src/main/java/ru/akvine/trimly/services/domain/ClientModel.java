package ru.akvine.trimly.services.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;
import ru.akvine.trimly.repositories.entities.ClientEntity;
import ru.akvine.trimly.services.domain.base.SoftModel;

@Data
@Accessors(chain = true)
public class ClientModel extends SoftModel {
    private Long id;
    private String uuid;
    private String username;
    @Nullable
    @ToString.Exclude
    private String hash;

    public ClientModel(ClientEntity clientEntity) {
        this.id = clientEntity.getId();
        this.uuid = clientEntity.getUuid();
        this.username = clientEntity.getUsername();
        this.hash = clientEntity.getHash();

        this.createdDate = clientEntity.getCreatedDate();
        this.updatedDate = clientEntity.getUpdatedDate();
        this.deletedDate = clientEntity.getDeletedDate();
        this.deleted = clientEntity.isDeleted();
    }
}

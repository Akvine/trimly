package ru.akvine.trimly.services.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.trimly.enums.LinkType;
import ru.akvine.trimly.repositories.entities.LinkEntity;
import ru.akvine.trimly.services.domain.base.Model;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class LinkModel extends Model {
    private Long id;
    private String uuid;
    private String originLink;
    private String shortLink;
    private LocalDateTime expiresAfter;
    private ClientModel client;
    private LinkType type;

    public LinkModel(LinkEntity linkEntity) {
        this.id = linkEntity.getId();
        this.uuid = linkEntity.getUuid();
        this.originLink = linkEntity.getOriginalLink();
        this.shortLink = linkEntity.getShortLink();
        this.type = linkEntity.getType();
        this.client = new ClientModel(linkEntity.getClient());

        this.createdDate = linkEntity.getCreatedDate();
        this.updatedDate = linkEntity.getUpdatedDate();
    }

    public boolean isExpires() {
        return LocalDateTime.now().isAfter(expiresAfter);
    }
}

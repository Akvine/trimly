package ru.akvine.trimly.repositories.entities.base;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class SoftBaseEntity extends BaseEntity {
    @Column(name = "IS_DELETED", nullable = false)
    private boolean deleted;

    @Column(name = "DELETED_DATE")
    private LocalDateTime deletedDate;
}

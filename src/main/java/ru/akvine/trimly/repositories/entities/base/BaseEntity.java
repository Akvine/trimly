package ru.akvine.trimly.repositories.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseEntity {
    @NotNull
    @Column(name = "CREATED_DATE", nullable = false)
    private final LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;
}

package ru.akvine.trimly.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.akvine.trimly.enums.LinkType;
import ru.akvine.trimly.repositories.entities.base.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "LINK_ENTITY")
@Entity
public class LinkEntity extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "linkEntitySequence")
    @SequenceGenerator(name = "linkEntitySequence", sequenceName = "SEQ_LINK_ENTITY", allocationSize = 1000)
    private Long id;

    @NotNull
    @Column(name = "UUID", updatable = false, nullable = false)
    private String uuid;

    @NotNull
    @Column(name = "SHORT_LINK", updatable = false, nullable = false)
    private String shortLink;

    @NotNull
    @Column(name = "ORIGINAL_LINK", nullable = false)
    private String originalLink;

    @NotNull
    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private LinkType type;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private ClientEntity client;

    @NotNull
    @Column(name = "EXPIRES_AFTER_DATE", nullable = false)
    private LocalDateTime expiresAfter;

    @Transient
    public boolean isExpires() {
        return LocalDateTime.now().isAfter(expiresAfter);
    }
}

package ru.akvine.trimly.repositories.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.akvine.trimly.repositories.entities.base.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ACCESS_TOKEN_ENTITY")
@Accessors(chain = true)
public class AccessTokenEntity extends BaseEntity {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accessTokenEntitySeq")
    @SequenceGenerator(name = "accessTokenEntitySeq", sequenceName = "SEQ_ACCESS_TOKEN_ENTITY", allocationSize = 1000)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

    @Column(name = "TOKEN", nullable = false)
    private String token;

    @Column(name = "EXPIRED_AT", nullable = false)
    private LocalDateTime expiredAt;

    @Transient
    public boolean isExpired() {
        return getCreatedDate().isAfter(expiredAt);
    }
}

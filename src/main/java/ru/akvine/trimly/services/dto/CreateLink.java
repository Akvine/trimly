package ru.akvine.trimly.services.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;
import ru.akvine.trimly.enums.LinkType;

@Data
@Accessors(chain = true)
public class CreateLink {
    private String clientUuid;
    private String originalLink;
    private LinkType linkType;
    @Nullable
    private Integer expiresAfterMinutes;
}

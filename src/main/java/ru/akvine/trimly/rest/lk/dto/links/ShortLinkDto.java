package ru.akvine.trimly.rest.lk.dto.links;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
public class ShortLinkDto {
    private String shortLink;
    private ZonedDateTime expiresAfter;
}

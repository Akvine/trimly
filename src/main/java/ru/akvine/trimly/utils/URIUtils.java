package ru.akvine.trimly.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@UtilityClass
public class URIUtils {
    public String extractHost(String link) {
        URI uri = parse(link);
        return Objects.requireNonNull(uri).getHost();
    }

    @Nullable
    public URI parse(String link) {
        try {
            return new URI(link);
        } catch (URISyntaxException exception) {
            return null;
        }
    }
}

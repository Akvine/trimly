package ru.akvine.trimly.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class DateUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER_DEFAULT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDateTime convertToLocalDateTime(@NotNull String date) {
        return convertToLocalDateTime(date, DATE_TIME_FORMATTER_DEFAULT);
    }

    public LocalDateTime convertToLocalDateTime(@NotNull String date, DateTimeFormatter dateTimeFormatter) {
        if (StringUtils.isBlank(date)) {
            throw new IllegalArgumentException("date can't be blank!");
        }
        return LocalDateTime.from(dateTimeFormatter.parse(date));
    }
}


package ru.akvine.trimly.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSessionException extends RuntimeException {
    public NoSessionException(String message) {
        super(message);
    }
}

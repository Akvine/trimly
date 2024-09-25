package ru.akvine.trimly.validators;

public interface Validator<T> {
    void validate(T obj);
}

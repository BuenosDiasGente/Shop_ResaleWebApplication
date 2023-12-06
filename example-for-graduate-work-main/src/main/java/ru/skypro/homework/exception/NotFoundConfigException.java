package ru.skypro.homework.exception;

import lombok.Getter;

@Getter
public class NotFoundConfigException extends RuntimeException {
    public NotFoundConfigException(String message) {
        super(message);
    }
}

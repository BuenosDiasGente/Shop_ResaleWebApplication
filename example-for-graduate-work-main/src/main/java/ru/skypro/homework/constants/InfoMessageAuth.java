package ru.skypro.homework.constants;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class InfoMessageAuth {
    public static final String SIZE_USER_NAME="Имя должно быть от 4 до 32 символов.";
    public static final String SIZE_PASSWORD=" Пароль должен быть от 8 до 16 символов.";
    public static final String SIZE_FIRST_NAME="" ;




//
//    @Size(min = 2, max = 16)
//    private String firstName;
//    @Size(min = 2, max = 16)
//    private String lastName;
//    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
//    private String phone;
    public static final String USER_NOT_FOUND_MSG = "Пользователь '%s' не найден!";
    public static final String ACCESS_DENIED_MSG = "Доступ запрещен!";
    public static final String USER_ALREADY_EXISTS = "Пользователь с данным адресом электронной почты уже существует!";
    public static final String NOT_FOUND_ENTITY = "Сущность не найдена!";
}


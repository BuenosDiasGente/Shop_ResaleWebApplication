package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDTO {
    @Size(min =3, max = 10)
    String name;

    @Size(min =3, max = 10)
    String surname;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    String phone;
}
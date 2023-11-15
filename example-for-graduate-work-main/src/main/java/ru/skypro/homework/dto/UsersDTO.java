package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.constants.Role;

@Data
public class UsersDTO {
    Long id;
    String login;
    String name;
    String surname;
    String phone;
    Role role;
    String image; //Image???
}

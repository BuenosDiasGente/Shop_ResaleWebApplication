package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.constants.Role;
import ru.skypro.homework.model.Image;

import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
public class UsersDTO {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private String role;
    // Image image; //Image???
    private byte[] picture;
}

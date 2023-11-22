package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.constants.Role;
import ru.skypro.homework.model.Image;

import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String LastName;
    private String phone;
    private Role role;
    private String image;
}

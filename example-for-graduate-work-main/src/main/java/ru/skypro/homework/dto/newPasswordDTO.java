package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class newPasswordDTO {
    @Size(min = 8, max = 16)
    String currentPassword;
    @Size(min = 8, max = 16)
    String newPassword;

}

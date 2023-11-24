package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    boolean setPassword(NewPasswordDTO newPasswordDTO);
    UserDTO getUser();
    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);

    boolean updateUserImage(MultipartFile image);
}

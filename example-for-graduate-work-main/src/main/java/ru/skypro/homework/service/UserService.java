package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    boolean updatePassword(NewPasswordDTO password);

    UserDTO getUser();

    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);


//    void updateUserImage(MultipartFile image);
}

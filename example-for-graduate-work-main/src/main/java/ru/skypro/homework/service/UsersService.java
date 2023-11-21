package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UsersDTO;
import ru.skypro.homework.model.Users;

public interface UsersService {
    boolean updatePassword(NewPasswordDTO password);

    UsersDTO getUser();

    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);


//    void updateUserImage(MultipartFile image);
}

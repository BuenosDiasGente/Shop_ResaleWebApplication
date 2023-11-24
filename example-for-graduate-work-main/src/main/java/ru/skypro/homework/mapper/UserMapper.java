package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User newPasswordDtoToUserEntity(NewPasswordDTO newPasswordDTO);
    UserDTO usersEntityToUsersDto(User user);
    UpdateUserDTO userEntityToUpdateUsersDto(User user);
    User updateUserDtoToUserEntity(UpdateUserDTO updateUserDTO);
    @Mapping(target = "email", source = "username")//пишем для несовподающих полей
    User registerDtoToUserEntity(RegisterDTO registerDTO);


}

package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UsersDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsersMapper {

// @Mapping(target = "email",source = "login") //пишем для несовподающих полей
// @Mapping(target = "picture",ignore = true) //заглушка поля,если не хотим его маппить
// UsersDTO usersEntityToUsersDto(Users users);
//
// UpdateUserDTO userEntityToUpdateUsersDto(Users users);
//// Users registerDtoToUserEntity(RegisterDTO registerDTO);

}

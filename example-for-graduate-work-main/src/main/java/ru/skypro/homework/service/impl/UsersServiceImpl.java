package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UsersDTO;
import ru.skypro.homework.exception.NotFoundConfigException;
import ru.skypro.homework.mapper.UsersMapper;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UsersService;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Override
    public boolean updatePassword(NewPasswordDTO password) {
        return false;
    }

    @Override
    public UsersDTO getUser() {
        //Если, аутентификация прошла успешно — это значит, имя и пароль верные.
        //Тогда объект Authentication сохраняется в SecurityContext, а тот, в свою очередь, — в SecurityContextHolder:
        //Authentication-объект, отражающий информацию о текущем пользователе и его привилегиях.
        //getPrincipal()-метод получения текущего пользователя
        //После  успешной аутентификации в поле Principal объекта Authentication будет реальный пользователь в виде UserDetails:
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Users user = usersRepository.findUserByUserName(username);
        return usersMapper.usersEntityToUsersDto(user);
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Users user = usersRepository.findUserByUserName(username);
        user.setName(updateUserDTO.getName());
        user.setSurname(updateUserDTO.getSurname());
        user.setPhone(updateUserDTO.getPhone());
        usersRepository.save(user);
        return usersMapper.userEntityToUpdateUsersDto(user);
    }

//    @Override
//    public void updateUserImage(MultipartFile image){
//    }


//    private Users findUserByLogin(Authentication authentication) {
//        // SecurityContextHolder.getContext().getAuthentication();
//        Users user = usersRepository.findUserByUserName(authentication.getName());
//        if (userLogin.isEmpty()) {
//            throw new NotFoundConfigException();
//        } else {
//          return userLogin.get();
//        }
//    }
}

package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.constants.Role;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.exception.AuthenticationException;
import ru.skypro.homework.exception.NotFoundConfigException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.service.AuthService;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;
import static ru.skypro.homework.exception.ExceptionMessageConst.INVALID_LOGIN_EXCEPTION;
import static ru.skypro.homework.exception.ExceptionMessageConst.INVALID_LOGIN_PASSWORD_EXCEPTION;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    /**
     * Интерфейс предоставляет основную информацию о пользователе.
     */
    private final MyUserDetailsService myUserDetailsService;

    /**
     * Интерфейс для выполнения односторонего преобразования пароля с целью его безопасного хранения
     */
    private final PasswordEncoder encoder;

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    /**
     * авторизация пользователя
     *
     * @param username
     * @param password
     * @return true or false
     */
    @Override
    @Transactional
    public boolean login(String username, String password) {
        UserDetails details = myUserDetailsService.loadUserByUsername(username);
        if (!userRepository.findUserByUserName(username).isPresent() || !encoder.matches(password, details.getPassword())) {
            throw new AuthenticationException(INVALID_LOGIN_PASSWORD_EXCEPTION);
        }
        return encoder.matches(password, details.getPassword());
    }

    /**
     * регистрация пользователя
     *
     * @param registerDto
     * @return
     */
    @Override
    @Transactional
    //@PreAuthorized (“hasRole(‘ADMIN’”)
    public boolean register(RegisterDTO registerDto) {
        log.info("AuthServiceImpl:-> register");

        if (userRepository.findUserByUserName(registerDto.getUsername()).isPresent()) {
            log.error("AuthServiceImpl: register: 'username' InvalidLogin");
            throw new NotFoundConfigException(INVALID_LOGIN_EXCEPTION);
        }

        User userEntity = userMapper.registerDtoToUserEntity(registerDto);
        String encode = encoder.encode(registerDto.getPassword());
        userEntity.setPassword(encode);

        if (isNull(registerDto.getRole())) {
            userEntity.setRole(Role.USER);
        }
        userRepository.save(userEntity);
        if (registerDto.getRole() == Role.ADMIN) {
            log.info("New ADMIN was created with username - {}", registerDto.getUsername());
        } else {
            log.info("New USER was created with username - {}", registerDto.getUsername());
        }
        return true;
    }

}

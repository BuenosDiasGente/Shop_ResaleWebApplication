package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.exception.NotFoundConfigException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;

import static java.util.Objects.isNull;
import static ru.skypro.homework.exception.ExceptionMessageConst.PASSWORD_NON_VALID;
import static ru.skypro.homework.exception.ExceptionMessageConst.USER_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final EntityManager entityManager;


    /**
     * метод matches проверяет, закодированный пароль, полученный из хранилища,
     * совпадает с отправленным необработанным паролем после того, как он тоже закодирован.
     * Возвращает true, если пароли совпадают, false, если они не совпадают.
     * Сам сохраненный пароль никогда не расшифровывается.
     * newPasswordDTO.getCurrentPassword() - исходный пароль для кодирования и сопоставления.
     * passwords- кодированный пароль из хранилища для сравнения.
     * метод encode - кодирует необработанный пароль
     *
     * @param newPasswordDTO
     * @return tru or false
     */
    @Override
    public boolean setPassword(NewPasswordDTO newPasswordDTO) {
        log.info("UserServiceImpl : ->setPassword");

        String username = objectAuthentication();
        User user = findUserByLoginWithCriteria(username);
        if (isNull(user)) {
            throw new NotFoundConfigException(USER_NOT_FOUND_EXCEPTION);
        }
        if (!passwordEncoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {

            throw new InvalidPasswordException(PASSWORD_NON_VALID);
        }
        String encode = passwordEncoder.encode(newPasswordDTO.getNewPassword());
        user.setPassword(encode);
        userRepository.save(user);
        return true;
    }


    @Override
    public UserDTO getUser() {
        String username = objectAuthentication();
        User user = findUserByLoginWithCriteria(username);
        if (isNull(user)) {
            throw new NotFoundConfigException(USER_NOT_FOUND_EXCEPTION);
        }
        UserDTO userDTO = usersMapper.usersEntityToUsersDto(user);
        return userDTO;
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        String username = objectAuthentication();
        User user = findUserByLoginWithCriteria(username);
        if (isNull(user)) {
            throw new NotFoundConfigException(USER_NOT_FOUND_EXCEPTION);
        } else
            user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setPhone(updateUserDTO.getPhone());
        userRepository.save(user);
        return usersMapper.userEntityToUpdateUsersDto(user);
    }

    @Override
    public boolean updateUserImage(MultipartFile image) throws IOException {
        String username = objectAuthentication();
        User user = findUserByLoginWithCriteria(username);
        if (isNull(user)) {
            throw new NotFoundConfigException(USER_NOT_FOUND_EXCEPTION);
        } else
            user.setImage(imageService.saveToDb(image));
        userRepository.save(user);
        return true;
    }

    /**
     * Если, аутентификация прошла успешно — это значит, имя и пароль верные.
     * Тогда объект Authentication сохраняется в SecurityContext, а тот, в свою очередь, — в SecurityContextHolder:
     * Authentication-объект, отражающий информацию о текущем пользователе и его привилегиях.
     * getPrincipal()-метод получения текущего пользователя
     * После  успешной аутентификации в поле Principal объекта Authentication будет реальный пользователь в виде UserDetails:
     *
     * @return UserDetails username
     */

    private String objectAuthentication() {
        Authentication authentications = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentications.getPrincipal()).getUsername();
    }


    /**
     * criteria API
     *
     * @param username
     * @return User
     */
    public User findUserByLoginWithCriteria(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root)
                .where(
                        criteriaBuilder.equal(root.get("email"), username));
        return entityManager.createQuery(query).getSingleResult();
    }

}

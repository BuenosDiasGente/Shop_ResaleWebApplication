package ru.skypro.homework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class MyUserPrincipal implements UserDetails {
    private final User user;

    public MyUserPrincipal(User user) {
        this.user = user;
    }

    /**
     * Возвращает полномочия, предоставленные пользователю.
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(user)
                .map(User::getRole)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(List::of)
                .orElse(Collections.emptyList());
    }

    /***
     * Возвращает пароль, используемый для аутентификации пользователя.
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /***
     * Возвращает имя пользователя, используемое для аутентификации пользователя.
     * @return
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Указывает, истек ли срок действия учетной записи пользователя.
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /***
     * Указывает, заблокирован или разблокирован пользователь.
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /***
     * Указывает, истек ли срок действия учетных данных пользователя (пароля).
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /***
     * Указывает, включен пользователь или отключен.
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

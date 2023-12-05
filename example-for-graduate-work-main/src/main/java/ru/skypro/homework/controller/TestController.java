package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.UserServiceImpl;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserServiceImpl userService;

//    @GetMapping("/test")
//    public String test(@RequestParam String username) {
//       return userService.findUserByLoginWithCriteria(username).toString();
//    }
}

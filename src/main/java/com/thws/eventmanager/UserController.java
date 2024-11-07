package com.thws.eventmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "/add")
    public void addUser(User user) {
        userService.addUser(user);
    }

    @DeleteMapping(path = "/delete")
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }
}

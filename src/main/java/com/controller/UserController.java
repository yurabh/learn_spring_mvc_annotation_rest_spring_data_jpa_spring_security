package com.controller;

import com.domain.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "users/user")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping(value = "users/user")
    public User get(@PathVariable("id") int id) {
        return userService.finById(id);
    }

    @PutMapping(value = "users/user")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping(value = "users/user")
    public void delete(@PathVariable("id") int id) {
        userService.deleteById(id);
    }
}

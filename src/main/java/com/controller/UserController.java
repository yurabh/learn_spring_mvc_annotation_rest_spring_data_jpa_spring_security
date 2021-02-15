package com.controller;

import com.domain.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping(value = "/find/{id}")
    public User get(@PathVariable("id") int id) {
        return userService.finById(id);
    }

    @Secured(value = "ROLE_ADMIN")
    @PutMapping(value = "/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @Secured(value = "ROLE_USER")
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.deleteById(id);
    }
}

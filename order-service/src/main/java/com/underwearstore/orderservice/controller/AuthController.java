package com.underwearstore.orderservice.controller;

import com.underwearstore.orderservice.entity.User;
import org.springframework.web.bind.annotation.*;
import com.underwearstore.orderservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public User registration(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {
        // ..
        return userService.registration(username, password, email);
    }

    @GetMapping("/login")
    public User login(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {
        // ..
        return userService.login(username, password, email);
    }

    @GetMapping("/reshresh")
    public User refresh(){
        // ..
        return userService.refresh();
    }
}

package com.underwearstore.orderservice.controller;

import com.underwearstore.orderservice.dto.RequestDto;
import com.underwearstore.orderservice.dto.ResponeDto;
import com.underwearstore.orderservice.entity.User;
import org.springframework.web.bind.annotation.*;
import com.underwearstore.orderservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration") // Браузер запоминает историю запросов в юрл, поэтому передавать пароль через @RequestParam нельзя. А у GetMapping нет общепринятой поддержки тела запроса.
    public ResponeDto registration(@RequestBody RequestDto requestDto) {
        User user = userService.registration(requestDto.username(), requestDto.password(), requestDto.email());
        return new ResponeDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), null);
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody RequestDto requestDto) {
        return userService.login(requestDto.email(), requestDto.password());
    }

//    @GetMapping("/refresh")
//    public User refresh(){
//        // ..
//        return userService.refresh();
//    }
}

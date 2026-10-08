package com.underwearstore.orderservice.service;

import org.springframework.stereotype.Service;
import com.underwearstore.orderservice.repository.UserRepository;
import com.underwearstore.orderservice.entity.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registration(String username, String password, String email){
        // ..
        return userRepository.save(user);
    }

    public User login(String username, String password, String email){
        // ..
        return user;
    }

    public User refresh(){
        // ..
        return user;
    }
}

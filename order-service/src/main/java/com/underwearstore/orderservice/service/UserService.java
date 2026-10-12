package com.underwearstore.orderservice.service;

import org.springframework.stereotype.Service;
import com.underwearstore.orderservice.repository.UserRepository;
import com.underwearstore.orderservice.entity.User;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registration(String username, String password, String email){

        boolean isExistingUsername = userRepository.findByUsername(username).isPresent();
        if(isExistingUsername){
            throw new RuntimeException("Пользователь с именем '" + username + "' уже существует");
        }

        boolean isExistingEmail = userRepository.findByEmail(email).isPresent();
        if(isExistingEmail){
            throw new RuntimeException("Пользователь с почтой '" + email + "' уже существует");
        }

        User user = new User(null, username, password, email, "user");

        return userRepository.save(user);
    }

    public Boolean login(String email, String password){
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            throw new RuntimeException("Пользователь с почтой '" + email + "' не зарегистрирован");
        }

        User user = userOpt.get();
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Неверный пароль!");
        }

        return true;
    }

//    public User refresh(){
//        // ..
//        return user;
//    }
}

package com.underwearstore.orderservice.service;

import com.underwearstore.orderservice.dto.TokenResponseDto;
import com.underwearstore.orderservice.entity.RefreshToken;
import com.underwearstore.orderservice.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import com.underwearstore.orderservice.repository.UserRepository;
import com.underwearstore.orderservice.entity.User;

import java.time.Instant;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
	private final RefreshTokenRepository refreshTokenRepository;

    public UserService(UserRepository userRepository, JwtService jwtService, RefreshTokenRepository refreshTokenRepository){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
		this.refreshTokenRepository = refreshTokenRepository;
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

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        User user = new User(null, username, hashedPassword, email, "user");

        return userRepository.save(user);
    }

    public TokenResponseDto login(String email, String password){
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            throw new RuntimeException("Пользователь с почтой '" + email + "' не зарегистрирован");
        }

		User user = userOpt.get();
		Long userId = user.getId();
		String refreshToken = jwtService.createRefreshToken(userId.toString());

        if(!(BCrypt.checkpw(password, user.getPassword()))){
            throw new RuntimeException("Неверный пароль!");
        }

		refreshTokenRepository.save(
			new RefreshToken(
				null,
				refreshToken,
				user,
				jwtService.getJwtExpiration(refreshToken).toInstant()
			)
		);

		return new TokenResponseDto(
				jwtService.createAccessToken(userId.toString())
		);
    }

    public TokenResponseDto refreshAccessToken(String refreshToken){
		if(!(jwtService.isRefreshToken(refreshToken))){
			throw new RuntimeException("Тип токена не является 'refresh'");
		}

		Optional<RefreshToken> refTokenOpt = refreshTokenRepository.findByRefreshToken(refreshToken);
		if(refTokenOpt.isEmpty()){
			throw new RuntimeException("В системе отсутствует запись с таким токеном");
		}

		RefreshToken refToken = refTokenOpt.get();
		String userId = jwtService.getSubject(refreshToken);

		if (!refToken.getExpiryDate().isAfter(Instant.now())) {
			throw new RuntimeException("Рефреш-токен устарел, логиньтесь заново");
		}

		if(!(refToken.getUser().getId().toString().equals(userId)) ){ // интересный мог бы быть баг
			throw new RuntimeException("Несовпадение айди юзера у токена");
		}

		String newAccessToken = jwtService.createAccessToken(userId);

		return new TokenResponseDto(newAccessToken);
    }
}

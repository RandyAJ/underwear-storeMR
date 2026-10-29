package com.underwearstore.orderservice.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

	@Value("${jwt.secret}")
   	private String secret;

   	String createAccessToken(String userId) {
		return Jwts.builder()
            .setSubject(userId)
            .signWith(generatehmacKey())
            .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
			.claim("tokenType", "access")
            .compact();
   }

   	String createRefreshToken(String userId){
		return Jwts.builder()
			.setSubject(userId)
            .signWith(generatehmacKey())
            .setExpiration(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)))
			.claim("tokenType", "refresh")
			.compact();
    }

	public boolean validateToken(String token){
		try {
        	Jwts.parserBuilder()
            	.setSigningKey(generatehmacKey())
                .build()
                .parseClaimsJws(token);
        } catch (JwtException e){
			System.out.println(e);
			return false;
        }

		return true;
    }

    public String getSubject(String token){
        if(validateToken(token)) {
            return Jwts.parserBuilder()
                    .setSigningKey(generatehmacKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } else {
            throw new RuntimeException("Токен невалиден");
        }
    }

    private Key generatehmacKey(){
        return new SecretKeySpec(Base64.getDecoder().decode(this.secret),
                SignatureAlgorithm.HS256.getJcaName());
    }

	public boolean isRefreshToken(String refreshToken){
	   try{
		   String tokenType = Jwts.parserBuilder()
				   .setSigningKey(generatehmacKey())
				   .build()
				   .parseClaimsJws(refreshToken)
				   .getBody()
				   .get("tokenType", String.class);

		   return tokenType.equals("refresh");

	   } catch (JwtException e){
		   System.out.println(e.getMessage());
	   }

		return false;
	}

	public boolean isAccessToken(String accessToken){
		try{
			String tokenType = Jwts.parserBuilder()
					.setSigningKey(generatehmacKey())
					.build()
					.parseClaimsJws(accessToken)
					.getBody()
					.get("tokenType", String.class);
			return tokenType.equals("access");

		} catch (JwtException e){
			System.out.println(e.getMessage());
		}

		return false;
	}

	public Date getJwtExpiration(String refreshToken){
		return Jwts.parserBuilder()
				.setSigningKey(generatehmacKey())
				.build()
				.parseClaimsJws(refreshToken)
				.getBody()
				.getExpiration();
	}

	// если дата истечения токена позже текущего времени - вернет true
	public boolean isTokenExpired(String token){
		return this.getJwtExpiration(token)
				.toInstant()
				.isAfter(Instant.now());
	}
}

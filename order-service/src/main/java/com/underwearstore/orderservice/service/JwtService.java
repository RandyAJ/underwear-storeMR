package com.underwearstore.orderservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    public String createJwt(String userId) {
        String secret = "1DVZB5aymGYMPpIkBtrYD2k5c3MXzwG9O3vMAGriEPc=";
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
            .setSubject(userId)
            .signWith(hmacKey)
            .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
            .compact();
    }
}

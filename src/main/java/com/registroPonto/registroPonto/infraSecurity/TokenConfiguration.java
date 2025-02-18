package com.registroPonto.registroPonto.infraSecurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.registroPonto.registroPonto.entities.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenConfiguration {

    @Value("${api.security.token.secret}")
    private String secret;


    //Geracao do Token
    public String generateToken(Users user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Instant instant = Instant.now();
            Instant expiration = instant.plus(2, ChronoUnit.HOURS);
            String token = JWT.create()
                    .withIssuer("api-punch-clock")
                    .withSubject(user.getEmail())
                    .withIssuedAt(Date.from(instant))
                    .withExpiresAt(Date.from(expiration))
                    .sign(algorithm);
            return token;
        }
        catch(JWTCreationException e){
            throw new RuntimeException("JWT generation failed", e);
        }
    }

    //Validacao do token
    public String validationToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api-punch-clock")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch(JWTVerificationException e){
            throw new RuntimeException("JWT validation failed", e);
        }
    }

}

package com.example.nupsisign.Infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.nupsisign.Model.Dbset.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private final SimpleControllerHandlerAdapter simpleControllerHandlerAdapter;
    @Value("${api.security.jwt.secret}")
    private String secret;

    public TokenService(SimpleControllerHandlerAdapter simpleControllerHandlerAdapter) {
        this.simpleControllerHandlerAdapter = simpleControllerHandlerAdapter;
    }

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create().withIssuer("NupSign")
                .withSubject(usuario.getEmail())
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                .withIssuer("NupSign")
                .build()
                .verify(token)
                .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

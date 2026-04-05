package com.backend.agendamento.config;

import com.backend.agendamento.entity.Customer;
import com.backend.agendamento.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenService {

    private final String secretKey = "my-super-secret-key-for-jwt-authentication-ordem-flow";

    public String genereteToken(User user){

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)),  SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserName(String token){
        return extractClaims(token.trim()).getSubject();
    }

    public boolean isTokenValid(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validaToken(String token, String username){
        final String extractUserName = extractUserName(token);

        return (extractUserName.equals(username) && !isTokenValid(token));

    }
}

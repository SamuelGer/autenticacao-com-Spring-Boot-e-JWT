package com.example.meu_primeiro_springboot.config_security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;
    private static final long EXPIRATION_TIME = 86400000; //86400000 = 24 Horas de duração do token

    @PostConstruct
    private void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    //Gera e retorna o token pro cliente.
    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
    //chamado em toda requisição  protegida para verificar a validade do token.
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
    //Salva o username para quem é o usuario e o tempo que ele ficará logado.
    //Ex: Sites onde o cliente acessa a conta e depois que ele retornar para o site antes da expiração do token, ele ficara logado
    public String extractUsername(String token){
        return Jwts.parser().verifyWith(secretKey)
                .build().parseSignedClaims(token).getPayload()
                .getSubject();
    }
}

package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.entity.User;
import com.therivex1907.accessmanagement.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String SECRET;

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) //manda el email
                .setIssuedAt(new Date())//hora de creacion
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//tiempo de duracion
                .signWith(getKey())//firmado con el key propio
                .compact();//convierte el token a devolver
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())//verifica si esta firmado
                .build()
                .parseClaimsJws(token)//si fue modificado, da error
                .getBody()//datos del payload
                .getSubject();//obtiene el email
    }
}

package com.rahim.ticketbooking.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // 32+ characters
    private static final String SECRET =
            "MBSTUICTJWTJAVABATCH!19SIGNOUT2027";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate JWT
    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract email
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic claim extractor
    public <T> T extractClaim(String token,
                              Function<Claims, T> resolver) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);
    }

    // Validate token
    public boolean isTokenValid(String token, String email) {

        return extractUsername(token).equals(email)
                && extractExpiration(token).after(new Date());
    }
}
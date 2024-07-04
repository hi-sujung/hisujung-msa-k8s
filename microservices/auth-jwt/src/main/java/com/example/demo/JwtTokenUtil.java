package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import java.security.Key;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(String loginId, long expireTimeMs) {

        // Create claims and add the loginId
        Claims claims = Jwts.claims().setSubject(loginId);
        claims.put("loginId", loginId);

        // Decode the base64 encoded key
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        // Build and sign the JWT token
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getLoginId(String token) {
        return extractClaims(token, secretKey).get("loginId").toString();
    }

    // 발급된 토큰이 만료 시간이 지났는지 체크
    public boolean isExpired(String token) {
        try {
            Date expiredDate = extractClaims(token, secretKey).getExpiration();
            return expiredDate.before(new Date());
        } catch (ExpiredJwtException e) {
            return true; // 토큰이 만료되면 true를 반환
        }
    }

    // SecretKey를 사용해 Token Parsing
    private Claims extractClaims(String token, String secretKey) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
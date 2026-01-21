package com.AbedProjects.ShopIt.User.Authentication;

import com.AbedProjects.ShopIt.User.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {

        if (jwtSecretKey == null || jwtSecretKey.length() < 32) {
            throw new IllegalStateException(
                    "JWT secret key must be at least 32 characters (256 bits)"
            );
        }

        return Keys.hmacShaKeyFor(
                jwtSecretKey.getBytes(StandardCharsets.UTF_8)
        );
    }


    public String generateJwtToken(UserEntity user)
    {
        return Jwts.builder().subject(user.getUsername()).
                claim("userid",user.getId().toString()).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis() + 1000*60*10)).
                signWith(getSecretKey()).
                compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey()).
                build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
//
//    public String getUsernameFromToken(String token) {
//        Claims claims = Jwts.parser()
//                .verifyWith(getSecretKey()).
//                build()
//                .parseSignedClaims(token)
//                .getPayload();
//        return claims.getSubject();
//    }
}

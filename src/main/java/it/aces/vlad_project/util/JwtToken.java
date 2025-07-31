package it.aces.vlad_project.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.aces.vlad_project.entity.RoleEntity;
import it.aces.vlad_project.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.*;

@Component
public class JwtToken {
    @Value("${jwt.accessTokenSecret}")
    private String accessSecret;
    @Value("${jwt.accessLifetime}")
    private Duration accessLifetime;

    public Map<String, String> generateToken(UserEntity userEntity) {
        Date issuedDate = new Date();
        Date expiryDateAccessToken = new Date(issuedDate.getTime() + accessLifetime.toMillis());
        byte[] decodedKeyAccessToken = Base64.getDecoder().decode(accessSecret);
        SecretKey keyAccessToken = new SecretKeySpec(decodedKeyAccessToken, 0, decodedKeyAccessToken.length,
                "HmacSHA256");
        List<String> roles = userEntity.getRole().stream()
                .map(RoleEntity::getTitle)
                .toList();
        Map<String, String> keysStorage = new HashMap<>();
        String accessToken = Jwts.builder()
                .setSubject(userEntity.getEmail())
                .claim("roles", roles)
                .setIssuedAt(issuedDate)
                .setExpiration(expiryDateAccessToken)
                .signWith(keyAccessToken, SignatureAlgorithm.HS256)
                .compact();
        keysStorage.put("accessToken", accessToken);
        return keysStorage;
    }

    public String getUser(String token, String secret) {
        return getAllClaimsFromToken(token, secret).getSubject();
    }

    public List<String> getRoles(String token, String secret) {
        return getAllClaimsFromToken(token, secret).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

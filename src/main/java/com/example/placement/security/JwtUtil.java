package com.example.placement.security;

import com.example.placement.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// Utility component for generating and validating JWT tokens.
@Component
public class JwtUtil {
    // Secret used to sign JWT tokens.
    private final SecretKey signingKey;
    // Token validity duration in milliseconds.
    private final long expiryMs;

    // Initializes key material and expiry settings from configuration.
    public JwtUtil(
            @Value("${app.jwt.secret:placement-jwt-secret-key-change-in-production-1234567890}") String secret,
            @Value("${app.jwt.expiry-ms:2592000000}") long expiryMs // 30 days
    ) {
        byte[] keyBytes = Arrays.copyOf(secret.getBytes(StandardCharsets.UTF_8), 32);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.expiryMs = expiryMs;
    }

    // Generates a JWT token containing email as subject and roles as a claim.
    public String generateToken(User user){
        List<String> roles = user.getRole() == null
                ? Collections.emptyList()
                : List.of(user.getRole().name());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiryMs))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();

    }

    // Extracts email from the JWT token subject.
    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    // Extracts role claims from JWT token.
    public List<String> extractRoles(String token) {
        Object roles = extractAllClaims(token).get("roles");
        if (roles instanceof List<?> roleList) {
            return roleList.stream().map(String::valueOf).toList();
        }
        return Collections.emptyList();
    }

    // Validates token signature, expiration, and expected email identity.
    public boolean validateToken(String token, String expectedEmail){
        try{
            Claims claims = extractAllClaims(token);
            String email = claims.getSubject();
            Date expiration = claims.getExpiration();
            return email != null
                    && email.equalsIgnoreCase(expectedEmail)
                    && expiration != null
                    && expiration.after(new Date());
        } catch (RuntimeException e) {
            return false;
        }
    }

    // Parses all claims from token after signature verification.
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

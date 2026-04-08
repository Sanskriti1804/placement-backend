package com.example.placement.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    //secret key used to sign JWT
    private final String SECRET = "keyyy";

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())        //token creation time
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();     // builds the token into a compact string(final JWT)

    }

    //extracting email from the JWT token
    public String extractEmail(String token){
        return Jwts.parser()    //created a jwt parser
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /// /validating whether the token is valid or not
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

}

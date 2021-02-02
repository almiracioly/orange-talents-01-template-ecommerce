package br.com.zup.ecommerce.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${ecommerce.jwt.expiration}")
    private String expiration;

    @Value("${ecommerce.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Date today = new Date();
        Date tokenExpiresAt = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer("ecommerce API")
                .setSubject(userDetails.getUser().getId().toString())
                .setIssuedAt(today)
                .setExpiration(tokenExpiresAt)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
}

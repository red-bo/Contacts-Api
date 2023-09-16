package com.red.bo.authentication;/*package com.red.bo.authentication;

import com.red.bo.core.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("nQy2e0trGijkNrgw/diKf4Y7axnli2c+Qd/ZpehNxzdqMmPb0TWOvsfCHcXcXzRcejQVfdGCtaZ+s971DObG0A==")
    private String jwtSecret;

    @Value("3600000")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // Gestion de l'erreur si la signature du jeton est invalide
        } catch (MalformedJwtException ex) {
            // Gestion de l'erreur si le jeton est mal formé
        } catch (ExpiredJwtException ex) {
            // Gestion de l'erreur si le jeton a expiré
        } catch (UnsupportedJwtException ex) {
            // Gestion de l'erreur si le type de jeton n'est pas pris en charge
        } catch (IllegalArgumentException ex) {
            // Gestion de l'erreur si le jeton est vide ou nul
        }
        return false;
    }
}

*/

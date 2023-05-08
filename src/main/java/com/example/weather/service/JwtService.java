package com.example.weather.service;

import com.example.weather.model.entity.Role;
import com.example.weather.model.entity.User;
import com.example.weather.utils.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public final long TOKEN_EXPIRE_DATE = 24 * 60 * 600;
    public final String TOKEN_SECRET_KEY = "THGjksdahjlbads6723jsdlHJSAJsclsajhicsdljkkdsk";

    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            username = getClaimFromToken(token, Claims::getSubject);
        } catch (IllegalArgumentException e) {
            logger.error("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            logger.error("JWT Token has expired");
        }
        return username;
    }

    public Date getExpirationDate(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(TOKEN_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        if (user.getFirstname() != null) {
            claims.put("firstname", user.getFirstname());
        }
        if (user.getLastname() != null)
            claims.put("lastname", user.getLastname());

        List<String> roles = user.getRoles().stream().map(Role::getName).map(Role.RoleType::name).toList();
        claims.put("roles", roles);
        long currentTime = new Date().getTime();

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + TOKEN_EXPIRE_DATE))
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET_KEY)
                .compact();
    }

    public boolean isExpireToken(String token) {
        return DateUtils.afterCurrentDate(getExpirationDate(token));
    }
}

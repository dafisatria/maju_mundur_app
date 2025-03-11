package com.bpd.maju_mundur.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bpd.maju_mundur.constant.ERole;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    private static final String SECRET_KEY = "asdfghjkl";
    private final Long EXPIRATION_TIME = 86400000L;

    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getSubject();
    }

    public Boolean validateToken(String token) {
        try {
            DecodedJWT decodedJWT = getDecodedJWT(token);
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public List<ERole> getRoleFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token);
        if (decodedJWT.getClaim("roles").isNull()) {
            return List.of();
        }
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return roles.stream()
                .map(ERole::valueOf)
                .collect(Collectors.toList());
    }

    public String generateToken(String username, List<ERole> roles) {
        List<String> roleNames = roles.stream()
                .map(Enum::name)
                .toList();

        return JWT.create()
                .withSubject(username)
                .withClaim("roles", roleNames)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    private static DecodedJWT getDecodedJWT(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token);
    }
}

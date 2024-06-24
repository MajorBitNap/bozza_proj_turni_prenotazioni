package com.majorbit.bozza_proj_turni_prenotazioni.util.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;
    @Value("${jwt.token.validity}")
    private Integer TOKEN_VALIDITY;

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getSignInKey() {
        byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(bytes, "HmacSHA256");
    }
//  metodo per generare token con claim extra
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        extraClaims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new java.util.Date(System.currentTimeMillis()))
                .expiration(new java.util.Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
//metodo per generare token senza extra claim
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isExpired(token);
    }

    public boolean isExpired(String token) {
        return extractExpiration(token).before(new java.util.Date(System.currentTimeMillis())); //setta come controllo la data attuale
    }

    private java.util.Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}

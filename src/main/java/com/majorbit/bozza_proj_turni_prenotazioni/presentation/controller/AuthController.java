package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginRequestDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private final String secretKey = "your_secret_key";
    private final long jwtExpirationInMs = 3600000; // 1 hour

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequestDTO loginRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.nome(), loginRequest.cognome()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.nome());
        return generateToken(userDetails);
    }

    private String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}


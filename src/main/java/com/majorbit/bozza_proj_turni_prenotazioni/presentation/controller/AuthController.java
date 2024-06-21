package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.TokenDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(
            @RequestBody RegisterDTO request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> authenticate(
            @RequestBody LoginDTO request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}

package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.TokenDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    public TokenDTO register(UtenteDTO request) {
        var utente = new Utente();
        utente.setNome(request.getNome());
        utente.setCognome(request.getCognome());
        utente.setEmail(request.getEmail());
        utente.setPassword(passwordEncoder.encode(request.getPassword()));
        utente.setRuolo(request.getRuolo());
        utenteRepository.save(utente);
        emailService.sendEmail(
                utente.getEmail(),
                "REGISTRAZIONE EFFETTUATA",
                "registrazione effettuata con successo"
        );
        return TokenDTO.builder()
                .token(jwtService.generateToken(utente))
                .build();
    }

    public TokenDTO authenticate(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var utente = utenteRepository.findByEmail(request.getUsername()).orElseThrow();
        return TokenDTO.builder()
                .token(jwtService.generateToken(utente))
                .build();
    }
}

package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.EmailDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class MailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailDTO emailDTO) {
            emailService.sendEmail(emailDTO.getToEmail(), emailDTO.getSubject(), emailDTO.getBody());
        return ResponseEntity.noContent().build();
    }

}

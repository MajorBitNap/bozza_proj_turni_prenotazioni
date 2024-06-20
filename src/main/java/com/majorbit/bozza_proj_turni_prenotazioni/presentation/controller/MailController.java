package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.EmailDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.JavaSmtpGmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
public class MailController {

    private final JavaSmtpGmailSenderService javaSmtpGmailSenderService;

    public MailController(JavaSmtpGmailSenderService javaSmtpGmailSenderService) {
        this.javaSmtpGmailSenderService = javaSmtpGmailSenderService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailDTO emailDTO) {
            javaSmtpGmailSenderService.sendEmail(emailDTO.getToEmail(), emailDTO.getSubject(), emailDTO.getBody());
        return ResponseEntity.noContent().build();
    }

}

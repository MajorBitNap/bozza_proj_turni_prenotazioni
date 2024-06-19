package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final GestioneLogin loginService;

    @Autowired
    public LoginController(GestioneLogin loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<LoginDTO> getLoginById(@PathVariable Long id) {
        LoginDTO ILoginDTO = loginService.getLoginById(id);
        return ResponseEntity.ok(ILoginDTO);
    }

    @GetMapping
    public ResponseEntity<List<LoginDTO>> getAllLogins() {
        List<LoginDTO> ILoginDTOS = loginService.getAllLogins();
        return ResponseEntity.ok(ILoginDTOS);
    }

    @PostMapping
    public ResponseEntity<LoginDTO> createLogin(@RequestBody LoginDTO ILoginDTO) {
        LoginDTO createdILoginDTO = loginService.createLogin(ILoginDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdILoginDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoginDTO> updateLogin(@PathVariable Long id, @RequestBody LoginDTO ILoginDTO) {
        LoginDTO updatedILoginDTO = loginService.updateLogin(id, ILoginDTO);
        return ResponseEntity.ok(updatedILoginDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long id) {
        loginService.deleteLogin(id);
        return ResponseEntity.noContent().build();
    }
}

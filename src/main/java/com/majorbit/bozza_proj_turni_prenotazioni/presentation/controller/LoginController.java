package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IGestioneLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final IGestioneLogin loginService;

    @Autowired
    public LoginController(IGestioneLogin loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<LoginDTO> getLoginById(@PathVariable Long id) {
        LoginDTO loginDTO = loginService.getLoginById(id);
        return ResponseEntity.ok(loginDTO);
    }

    @GetMapping
    public ResponseEntity<List<LoginDTO>> getAllLogins() {
        List<LoginDTO> loginDTOs = loginService.getAllLogins();
        return ResponseEntity.ok(loginDTOs);
    }

    @PostMapping
    public ResponseEntity<LoginDTO> createLogin(@RequestBody LoginDTO loginDTO) {
        LoginDTO createdLoginDTO = loginService.createLogin(loginDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoginDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoginDTO> updateLogin(@PathVariable Long id, @RequestBody LoginDTO loginDTO) {
        LoginDTO updatedLoginDTO = loginService.updateLogin(id, loginDTO);
        return ResponseEntity.ok(updatedLoginDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long id) {
        loginService.deleteLogin(id);
        return ResponseEntity.noContent().build();
    }
}

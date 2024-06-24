package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestionePosto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posti")
public class PostoController {

    private final GestionePosto gestionePosto;

    @GetMapping("/{id}")
    public ResponseEntity<PostoDTO> getPostoById(@PathVariable Integer id) {
        var posto = gestionePosto.getPostoById(id);
        return ResponseEntity.ok(posto);
    }

    @GetMapping
    public ResponseEntity<List<PostoDTO>> getAllPosti() {
        List<PostoDTO> posti = gestionePosto.getAllPosti();
        return ResponseEntity.ok(posti);
    }

    @PostMapping
    public ResponseEntity<PostoDTO> createPosto(@RequestBody PostoDTO posto) {
        var newPosto = gestionePosto.createPosto(posto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPosto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostoDTO> updatePosto(@PathVariable Integer id, @RequestBody PostoDTO posto) {
        var updatedPosto = gestionePosto.updatePosto(id, posto);
        return ResponseEntity.ok(updatedPosto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosto(@PathVariable Integer id) {
        gestionePosto.deletePosto(id);
        return ResponseEntity.noContent().build();
    }
}

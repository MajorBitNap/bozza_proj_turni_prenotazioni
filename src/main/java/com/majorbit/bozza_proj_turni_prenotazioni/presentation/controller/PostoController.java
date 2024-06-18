package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestionePosto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posti")
public class PostoController {

    private final GestionePosto gestionePosto;

    @Autowired
    public PostoController(GestionePosto gestionePosto) {
        this.gestionePosto = gestionePosto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostoDTO> getPostoById(@PathVariable Long id) {
        PostoDTO posto = gestionePosto.getPostoById(id);
        return ResponseEntity.ok(posto);
    }

    @GetMapping
    public ResponseEntity<List<PostoDTO>> getAllPosti() {
        List<PostoDTO> posti = gestionePosto.getAllPosti();
        return ResponseEntity.ok(posti);
    }

    @PostMapping
    public ResponseEntity<PostoDTO> createPosto(@RequestBody PostoDTO posto) {
        PostoDTO newPosto = gestionePosto.createPosto(posto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPosto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostoDTO> updatePosto(@PathVariable Long id, @RequestBody PostoDTO posto) {
        PostoDTO updatedPosto = gestionePosto.updatePosto(id, posto);
        return ResponseEntity.ok(updatedPosto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosto(@PathVariable Long id) {
        gestionePosto.deletePosto(id);
        return ResponseEntity.noContent().build();
    }
}

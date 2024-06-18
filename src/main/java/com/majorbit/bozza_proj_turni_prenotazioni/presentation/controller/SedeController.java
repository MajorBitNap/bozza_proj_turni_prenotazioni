package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneSede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sedi")
public class SedeController {

    private final GestioneSede gestioneSede;

    @Autowired
    public SedeController(GestioneSede gestioneSede) {
        this.gestioneSede = gestioneSede;
    }

    @PostMapping
    public ResponseEntity<SedeDTO> createSede(@RequestBody SedeDTO sede) {
        SedeDTO newSede = gestioneSede.createSede(sede);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSede);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> getSedeById(@PathVariable Long id) {
        SedeDTO sede = gestioneSede.getSedeById(id);
        return ResponseEntity.ok(sede);
    }

    @GetMapping
    public ResponseEntity<List<SedeDTO>> getAllSedi() {
        List<SedeDTO> sedi = gestioneSede.getAllSedi();
        return ResponseEntity.ok(sedi);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SedeDTO> updateSede(@PathVariable Long id, @RequestBody SedeDTO sede) {
        SedeDTO updatedSede = gestioneSede.updateSede(id, sede);
        return ResponseEntity.ok(updatedSede);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable Long id) {
        gestioneSede.deleteSede(id);
        return ResponseEntity.noContent().build();
    }

}

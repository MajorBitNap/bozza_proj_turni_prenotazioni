package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sedi")
public class SedeController {

    private final SedeService sedeService;

    @Autowired
    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> getSedeById(@PathVariable Long id) {
        SedeDTO sede = sedeService.getSedeById(id);
        return ResponseEntity.ok(sede);
    }

    @GetMapping
    public ResponseEntity<List<SedeDTO>> getAllSedi() {
        List<SedeDTO> sedi = sedeService.getAllSedi();
        return ResponseEntity.ok(sedi);
    }

    @PostMapping
    public ResponseEntity<SedeDTO> createSede(@RequestBody SedeDTO sede) {
        SedeDTO newSede = sedeService.createSede(sede);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSede);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SedeDTO> updateSede(@PathVariable Long id, @RequestBody @RequestBody SedeDTO sede) {
        SedeDTO updatedSede = sedeService.updateSede(id, sede);
        return ResponseEntity.ok(updatedSede);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable Long id) {
        sedeService.deleteSede(id);
        return ResponseEntity.noContent().build();
    }

}

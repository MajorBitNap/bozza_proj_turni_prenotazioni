package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller.amministratore;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneSede;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('AMMINISTRATORE')")
@RequestMapping("/api/v1/sedi")
public class SedeController {

    private final GestioneSede gestioneSede;

    @PostMapping
    public ResponseEntity<SedeDTO> createSede(@RequestBody SedeDTO sede) {
        var newSede = gestioneSede.createSede(sede);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSede);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> getSedeById(@PathVariable Integer id) {
        var sede = gestioneSede.getSedeById(id);
        return ResponseEntity.ok(sede);
    }

    @GetMapping
    public ResponseEntity<List<SedeDTO>> getAllSedi() {
        List<SedeDTO> sedi = gestioneSede.getAllSedi();
        return ResponseEntity.ok(sedi);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SedeDTO> updateSede(@PathVariable Integer id, @RequestBody SedeDTO sede) {
        var updatedSede = gestioneSede.updateSede(id, sede);
        return ResponseEntity.ok(updatedSede);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable Integer id) {
        gestioneSede.deleteSede(id);
        return ResponseEntity.noContent().build();
    }

}

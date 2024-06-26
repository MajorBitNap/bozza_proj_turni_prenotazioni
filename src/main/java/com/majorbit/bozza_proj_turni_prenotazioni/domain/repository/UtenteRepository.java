package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    Optional<Utente> findByEmail(String email);

}

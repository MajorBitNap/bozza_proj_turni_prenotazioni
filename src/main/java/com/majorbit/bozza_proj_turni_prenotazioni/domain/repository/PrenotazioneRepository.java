package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByStanzaAndData(Long stanzaId, Date data);
}

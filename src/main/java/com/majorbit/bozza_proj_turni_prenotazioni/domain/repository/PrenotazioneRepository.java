package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    @Query("SELECT p FROM Prenotazione p " + "WHERE p.posto = :posto "
            + "AND p.dataInizio BETWEEN :dataInizio AND :dataFine "
            + "AND p.dataFine BETWEEN :dataInizio AND :dataFine")
    List<Prenotazione> findPrenotazioniInDateRange(
            @Param("posto") Posto posto,
            @Param("dataInizio") Date dataInizio,
            @Param("dataFine") Date dataFine
    );

}

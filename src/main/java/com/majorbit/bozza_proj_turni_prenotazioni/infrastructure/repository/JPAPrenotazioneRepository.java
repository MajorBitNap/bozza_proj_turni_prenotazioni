package com.majorbit.bozza_proj_turni_prenotazioni.infrastructure.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface JPAPrenotazioneRepository extends JpaRepository<Prenotazione, Long>, PrenotazioneRepository {
    List<Prenotazione> findByPostoAndDataInizioAndDataFine(Posto posto, Date dataInizio, Date dataFine);
}

package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostoRepository  extends JpaRepository<Posto, Integer> {

    @Query("""
            SELECT p FROM Posto p WHERE p.disponibile = true
            AND p.id NOT IN (SELECT pr.posto.id FROM Prenotazione
            pr WHERE pr.stato = 'accettata')
            """)
    List<Posto> getPostiDisponibili();

}

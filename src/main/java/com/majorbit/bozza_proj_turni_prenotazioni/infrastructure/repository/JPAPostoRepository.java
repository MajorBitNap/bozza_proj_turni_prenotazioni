package com.majorbit.bozza_proj_turni_prenotazioni.infrastructure.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAPostoRepository extends JpaRepository<Posto, Integer>, PostoRepository {

    @Query("""
            SELECT p FROM Posto p WHERE p.disponibile = true
            AND p.id NOT IN (SELECT pr.posto.id FROM Prenotazione
            pr WHERE pr.stato = 'accettata')
            """)
    List<Posto> getPostiDisponibili();

}

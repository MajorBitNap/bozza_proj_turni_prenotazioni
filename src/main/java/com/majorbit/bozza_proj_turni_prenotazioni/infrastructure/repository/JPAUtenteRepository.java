package com.majorbit.bozza_proj_turni_prenotazioni.infrastructure.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAUtenteRepository extends JpaRepository<Utente, Long>, UtenteRepository {


}

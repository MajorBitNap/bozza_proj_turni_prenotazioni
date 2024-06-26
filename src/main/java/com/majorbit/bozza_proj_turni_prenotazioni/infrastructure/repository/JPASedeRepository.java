package com.majorbit.bozza_proj_turni_prenotazioni.infrastructure.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPASedeRepository extends JpaRepository<Sede, Integer>, SedeRepository {
}

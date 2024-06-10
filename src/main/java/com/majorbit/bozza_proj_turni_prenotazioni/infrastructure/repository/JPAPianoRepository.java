package com.majorbit.bozza_proj_turni_prenotazioni.infrastructure.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAPianoRepository extends JpaRepository<Piano, Long>, PianoRepository {
}

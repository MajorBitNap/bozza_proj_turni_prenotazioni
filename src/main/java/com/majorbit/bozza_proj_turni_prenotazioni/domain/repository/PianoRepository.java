package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PianoRepository extends JpaRepository<Piano, Integer> {

}

package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SedeRepository  extends JpaRepository<Sede, Integer> {
    Sede findSedeById(Integer id);
}

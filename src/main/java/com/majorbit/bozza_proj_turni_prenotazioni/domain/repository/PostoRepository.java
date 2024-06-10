package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostoRepository  extends JpaRepository<Posto, Long> {
}

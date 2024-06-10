package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StanzaRepostory extends JpaRepository<Stanza, Long> {
}

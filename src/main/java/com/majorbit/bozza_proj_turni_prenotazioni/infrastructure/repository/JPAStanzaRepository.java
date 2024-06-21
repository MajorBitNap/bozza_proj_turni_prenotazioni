package com.majorbit.bozza_proj_turni_prenotazioni.infrastructure.repository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.StanzaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAStanzaRepository extends JpaRepository<Stanza, Integer>, StanzaRepository{
}

package com.majorbit.bozza_proj_turni_prenotazioni.domain.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {

}

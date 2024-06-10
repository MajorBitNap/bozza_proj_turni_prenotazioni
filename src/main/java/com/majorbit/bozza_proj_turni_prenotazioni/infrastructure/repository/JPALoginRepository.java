package com.majorbit.bozza_proj_turni_prenotazioni.infrastructure.repository;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.LoginRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPALoginRepository extends JpaRepository<Login, Long>, LoginRepository {
}

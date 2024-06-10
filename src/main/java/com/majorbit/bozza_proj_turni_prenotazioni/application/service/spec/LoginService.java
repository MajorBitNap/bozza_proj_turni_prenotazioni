package com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;

import java.util.List;

public interface LoginService {
    LoginDTO createLogin(LoginDTO loginDTO);
    LoginDTO getLoginById(Long id);
    List<LoginDTO> getAllLogins();
    LoginDTO updateLogin(Long id, LoginDTO loginDTO);
    void deleteLogin(Long id);
}

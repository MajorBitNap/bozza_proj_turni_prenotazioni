package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;

public class LoginMapper {

    public static LoginDTO toDTO(Login login) {
        return new LoginDTO(
                login.getId(),
                login.getUtenteId(),
                login.getPasswordUtente()
        );
    }

    public static Login toEntity(LoginDTO loginDTO) {
        Login login = new Login();
        login.setId(loginDTO.id());
        login.setUtenteId(loginDTO.utenteId());
        login.setPasswordUtente(loginDTO.utentePassword());
        return login;
    }
}

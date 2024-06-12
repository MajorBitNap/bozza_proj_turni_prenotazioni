package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public static LoginDTO toDTO(Login login) {
        return new LoginDTO(
                login.getId(),
                login.getUtente(),
                login.getPasswordUtente()
        );
    }

    public static Login toEntity(LoginDTO loginDTO) {
        Login login = new Login();
        login.setId(loginDTO.id());
        login.setUtente(loginDTO.utente());
        login.setPasswordUtente(loginDTO.utentePassword());
        return login;
    }
}

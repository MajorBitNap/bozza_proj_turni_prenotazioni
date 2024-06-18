package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public static LoginDTO toDTO(Login login) {
        return new LoginDTO(
                login.getUtente(),
                login.getPassword()
        );
    }

    public static Login toEntity(LoginDTO loginDTO) {
        Login login = new Login();
        login.setUtente(loginDTO.getUtente());
        login.setPassword(loginDTO.getPassword());
        return login;
    }
}

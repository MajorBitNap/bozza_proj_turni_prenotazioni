package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginDTO toDTO(Login login) {
        return new LoginDTO(
                login.getUsername(),
                login.getPassword()
        );
    }

    public Login toEntity(LoginDTO loginDTO) {
        var login = new Login();
        login.setUsername(loginDTO.getUsername());
        login.setPassword(loginDTO.getPassword());
        return login;
    }
}

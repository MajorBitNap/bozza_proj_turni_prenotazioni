package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginDTO toDTO(Login login) {
        return LoginDTO.builder()
                .username(login.getUsername())
                .password(login.getPassword())
                .build();
    }

    public Login toEntity(LoginDTO loginDTO) {
        return Login.builder()
                .username(loginDTO.getUsername())
                .password(loginDTO.getPassword())
                .build();
    }
}

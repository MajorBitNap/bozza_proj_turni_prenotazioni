package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

}

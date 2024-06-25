package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.LoginMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.LoginRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestioneLogin {

    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;

    public LoginDTO createLogin(LoginDTO ILoginDTO) {
        var login = loginMapper.toEntity(ILoginDTO);
        var savedLogin = loginRepository.save(login);
        return loginMapper.toDTO(savedLogin);
    }

    public LoginDTO getLoginById(Integer id) {
        var login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return loginMapper.toDTO(login);
    }

    public List<LoginDTO> getAllLogins() {
        List<Login> logins = loginRepository.findAll();
        return logins.stream().map(loginMapper::toDTO).collect(Collectors.toList());
    }

    public LoginDTO updateLogin(Integer id, LoginDTO ILoginDTO) {
        var login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        login.setPassword(ILoginDTO.getPassword());
        login.setUsername(     ILoginDTO.getUsername());
        var updatedLogin = loginRepository.save(login);
        return loginMapper.toDTO(updatedLogin);
    }

    public void deleteLogin(Integer id) {
        var login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        loginRepository.delete(login);
    }
}

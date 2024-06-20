package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.LoginMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.LoginRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestioneLogin {

    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;

    @Autowired
    public GestioneLogin(LoginRepository loginRepository, LoginMapper loginMapper) {
        this.loginRepository = loginRepository;
        this.loginMapper = loginMapper;
    }

    public LoginDTO createLogin(LoginDTO ILoginDTO) {
        Login login = loginMapper.toEntity(ILoginDTO);
        Login savedLogin = loginRepository.save(login);
        return loginMapper.toDTO(savedLogin);
    }

    public LoginDTO getLoginById(Long id) {
        Login login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Login not found"));
        return loginMapper.toDTO(login);
    }

    public List<LoginDTO> getAllLogins() {
        List<Login> logins = loginRepository.findAll();
        return logins.stream().map(loginMapper::toDTO).collect(Collectors.toList());
    }

    public LoginDTO updateLogin(Long id, LoginDTO ILoginDTO) {
        Login login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Login not found"));
        login.setPassword(ILoginDTO.getPassword());
        login.setEmail(     ILoginDTO.getEmail());
        Login updatedLogin = loginRepository.save(login);
        return loginMapper.toDTO(updatedLogin);
    }

    public void deleteLogin(Long id) {
        Login login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Login not found"));
        loginRepository.delete(login);
    }
}

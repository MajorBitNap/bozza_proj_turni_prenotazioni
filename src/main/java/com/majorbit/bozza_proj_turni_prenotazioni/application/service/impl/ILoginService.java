package com.majorbit.bozza_proj_turni_prenotazioni.application.service.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.LoginMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.LoginService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ILoginService implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginDTO createLogin(LoginDTO loginDTO) {
        Login login = loginMapper.toEntity(loginDTO);
        Login savedLogin = loginRepository.save(login);
        return loginMapper.toDTO(savedLogin);
    }

    @Override
    public LoginDTO getLoginById(Long id) {
        Login login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Login not found"));
        return loginMapper.toDTO(login);
    }

    @Override
    public List<LoginDTO> getAllLogins() {
        List<Login> logins = loginRepository.findAll();
        return logins.stream().map(loginMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public LoginDTO updateLogin(Long id, LoginDTO loginDTO) {
        Login login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Login not found"));
        login.setPassword(loginDTO.getPassword());
        login.setUtente(loginMapper.toEntity(loginDTO.getUtente()));
        Login updatedLogin = loginRepository.save(login);
        return loginMapper.toDTO(updatedLogin);
    }

    @Override
    public void deleteLogin(Long id) {
        Login login = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Login not found"));
        loginRepository.delete(login);
    }
}

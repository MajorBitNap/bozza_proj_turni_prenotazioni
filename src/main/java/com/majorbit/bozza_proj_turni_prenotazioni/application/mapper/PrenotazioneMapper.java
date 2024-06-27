package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrenotazioneMapper {

    private final UtenteRepository utenteRepository;

    private final PostoRepository postoRepository;

    public PrenotazioneDTO toDTO(Prenotazione prenotazione) {
        return PrenotazioneDTO.builder()
                .dataInizio(prenotazione.getDataInizio())
                .dataFine(prenotazione.getDataFine())
                .stato(prenotazione.getStato())
                .posto(prenotazione.getPosto().getId())
                .utente(prenotazione.getUtente().getId())
                .build();
    }

    public Prenotazione toEntity(PrenotazioneDTO prenotazioneDTO) {
        return Prenotazione.builder()
                .dataInizio(prenotazioneDTO.getDataInizio())
                .dataFine(prenotazioneDTO.getDataFine())
                .stato(prenotazioneDTO.getStato())
                .posto(postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow(() -> new ResourceNotFoundException("Resource not found")))
                .utente(utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow(() -> new ResourceNotFoundException("Resource not found")))
                .build();
    }
}
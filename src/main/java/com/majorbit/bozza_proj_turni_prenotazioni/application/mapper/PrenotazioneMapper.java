package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapper {

    public static PrenotazioneDTO toDTO(Prenotazione prenotazione) {
        Posto posto = prenotazione.getPosto();
        Utente utente = prenotazione.getUtente();
        return new PrenotazioneDTO(
                prenotazione.getDataInizio(),
                prenotazione.getDataFine(),
                prenotazione.getStato(),
                PostoMapper.toDTO(posto),
                UtenteMapper.toDTO(utente)
        );
    }

    public static Prenotazione toEntity(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = new Prenotazione();
        UtenteDTO utenteDTO = prenotazioneDTO.getUtente();
        PostoDTO postoDTO = prenotazioneDTO.getPosto();
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setStato(prenotazioneDTO.getStato());
        UtenteMapper.toEntity(utenteDTO);
        PostoMapper.toEntity(postoDTO);
        return prenotazione;
    }

}

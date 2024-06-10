package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;

public class PrenotazioneMapper {

    public static PrenotazioneDTO toDTO(Prenotazione prenotazione) {
        return new PrenotazioneDTO(
                prenotazione.getId(),
                prenotazione.getDataInizio(),
                prenotazione.getDataFine(),
                prenotazione.getStato(),
                prenotazione.getPostoId(),
                prenotazione.getUtenteId()
        );
    }

    public static Prenotazione toEntity(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setId(prenotazioneDTO.id());
        prenotazione.setDataInizio(prenotazioneDTO.dataInizio());
        prenotazione.setDataFine(prenotazioneDTO.dataFine());
        prenotazione.setStato(prenotazioneDTO.stato());
        prenotazione.setPostoId(prenotazioneDTO.postoId());
        prenotazione.setUtenteId(prenotazioneDTO.utenteId());
        return prenotazione;
    }

}

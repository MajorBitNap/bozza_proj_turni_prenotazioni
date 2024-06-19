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
        return new PrenotazioneDTO(
                prenotazione.getDataInizio(),
                prenotazione.getDataFine(),
                prenotazione.getStato(),
                prenotazione.getPosto(),
                prenotazione.getUtente()
        );
    }

    public static Prenotazione toEntity(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setStato(prenotazioneDTO.getStato());
        prenotazione.setUtente(prenotazioneDTO.getUtente());
        prenotazione.setPosto(prenotazioneDTO.getPosto());
        return prenotazione;
    }
}
package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.DateRequestDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;

import java.util.List;

public interface RecuperaPostiDisponibili {
    public List<PostoDTO> getPostiDisponibili();
}

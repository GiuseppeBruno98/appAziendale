package com.example.AppAziendale.mappers;

import com.example.AppAziendale.domains.Entities.PosizioneLavorativa;
import com.example.AppAziendale.domains.dto.requests.PosizioneLavorativaRequest;
import com.example.AppAziendale.domains.dto.responses.PosizioneLavorativaResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.DipartimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosizioneLavorativaMapper {

    @Autowired
    DipartimentoService dipartimentoService;

    public PosizioneLavorativa fromCreatePosizoneLavorativaRequest(PosizioneLavorativaRequest request) throws MyEntityNotFoundException {
        return PosizioneLavorativa
                .builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .idDipartimento(dipartimentoService.getById(request.idDipartimento().id()))
                .build();
    }

    public PosizioneLavorativaResponse toPosizioneLavorativaResponse(PosizioneLavorativa request) throws MyEntityNotFoundException {
        return PosizioneLavorativaResponse
                .builder()
                .id(request.getId())
                .nome(request.getNome())
                .descrizione(request.getDescrizione())
                .idDipartimento(request.getIdDipartimento().getId())
                .build();
    }
}

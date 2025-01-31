package com.example.AppAziendale.mappers;

import com.example.AppAziendale.domains.Entities.Dipartimento;
import com.example.AppAziendale.domains.dto.requests.DipartimentoRequest;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DipartimentoMapper {

    public Dipartimento fromCreateDipartimentoRequest(DipartimentoRequest request) throws MyEntityNotFoundException {
        return Dipartimento
                .builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .build();
    }

}

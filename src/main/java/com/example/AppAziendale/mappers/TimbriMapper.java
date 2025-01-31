package com.example.AppAziendale.mappers;


import com.example.AppAziendale.domains.Entities.Timbri;
import com.example.AppAziendale.domains.Entities.Utente;
import com.example.AppAziendale.domains.dto.requests.CreateTimbriRequest;
import com.example.AppAziendale.domains.dto.responses.TimbriResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimbriMapper {

    @Autowired
    UtenteService utenteService;


    public Timbri fromCreateTimbriRequest(CreateTimbriRequest request, Utente utente) throws MyEntityNotFoundException {

        return Timbri
                .builder()
                .utenteId(utente) // Passa l'oggetto Utente
                .oraInizio(LocalDateTime.now()) // Imposta l'ora di inizio
                .build();
    }


    public TimbriResponse toTimbriResponse(Timbri request) throws MyEntityNotFoundException {
        return TimbriResponse
                .builder()
                .utenteId(request.getUtenteId().getId())
                .oraInzio(request.getOraInizio())
                .oraFine(request.getOraFine())
                .inizioPausa(request.getInizioPausa())
                .finePausa(request.getFinePausa())
                .build();
    }


}

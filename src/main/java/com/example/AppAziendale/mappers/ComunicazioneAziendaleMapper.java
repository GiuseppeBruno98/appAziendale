package com.example.AppAziendale.mappers;


import com.example.AppAziendale.domains.Entities.ComunicazioneAziendale;
import com.example.AppAziendale.domains.Entities.ComunicazioneAziendaleScheduled;
import com.example.AppAziendale.domains.dto.requests.CreateComunicazioneAziendaleRequest;
import com.example.AppAziendale.domains.dto.requests.CreateComunicazioneAziendaleScheduledRequest;
import com.example.AppAziendale.domains.dto.responses.ComunicazioneAziendaleResponse;
import com.example.AppAziendale.domains.dto.responses.ComunicazioneAziendaleScheduledResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComunicazioneAziendaleMapper {

    @Autowired
    private UtenteService utenteService;

    public ComunicazioneAziendale fromCreateComunicazioneAziendaleRequest(CreateComunicazioneAziendaleRequest request) throws MyEntityNotFoundException {
        return ComunicazioneAziendale
                .builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .creatorId(utenteService.getById(request.creatorId().id()))
                .build();
    }

    public ComunicazioneAziendaleScheduled fromCreateComunicazioneAziendaleScheduledRequestToComunicazioneAziendaleScheduled(CreateComunicazioneAziendaleScheduledRequest request) throws MyEntityNotFoundException {
        return ComunicazioneAziendaleScheduled.builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .publishTime(request.publishTime())
                .creatorId(utenteService.getById(request.creatorId().id()))
                .build();
    }

    public CreateComunicazioneAziendaleRequest fromCreateComunicazioneAziendaleScheduledRequestToCreateComunicazioneAziendaleRequest(CreateComunicazioneAziendaleScheduledRequest request) throws MyEntityNotFoundException {
        return CreateComunicazioneAziendaleRequest.builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .creatorId(request.creatorId())
                .build();
    }

    public ComunicazioneAziendaleResponse toComunicazioneAziendaleResponse(ComunicazioneAziendale request) throws MyEntityNotFoundException {
        return ComunicazioneAziendaleResponse
                .builder()
                .id(request.getId())
                .titolo(request.getTitolo())
                .contenuto(request.getContenuto())
                .creatorId(request.getId())
                .build();
    }

    public ComunicazioneAziendaleScheduledResponse toComunicazioneAziendaleScheduledResponse(ComunicazioneAziendaleScheduled request) throws MyEntityNotFoundException {
        return ComunicazioneAziendaleScheduledResponse.builder()
                .id(request.getId())
                .titolo(request.getTitolo())
                .contenuto(request.getContenuto())
                .publishTime(request.getPublishTime())
                .creatorId(request.getCreatorId().getId())
                .build();
    }
}




package com.example.AppAziendale.mappers;

import com.example.AppAziendale.domains.Entities.News;
import com.example.AppAziendale.domains.Entities.NewsScheduled;
import com.example.AppAziendale.domains.dto.requests.CreateNewsRequest;
import com.example.AppAziendale.domains.dto.requests.CreateNewsScheduledRequest;
import com.example.AppAziendale.domains.dto.responses.NewsResponse;
import com.example.AppAziendale.domains.dto.responses.NewsScheduledResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsMapper {

    @Autowired
    private UtenteService utenteService;

    public News fromCreateNewsRequest(CreateNewsRequest request) throws MyEntityNotFoundException {
        return News.builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .immagine(request.immagine())
                .creatorId(utenteService.getById(request.creatorId().id()))
                .build();
    }

    public NewsScheduled fromCreateNewsScheduledRequestToNewsScheduled(CreateNewsScheduledRequest request) throws MyEntityNotFoundException {
        return NewsScheduled.builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .immagine(request.immagine())
                .publishTime(request.publishTime())
                .creatorId(utenteService.getById(request.creatorId().id()))
                .build();
    }

    public CreateNewsRequest fromCreateNewsScheduledRequestToCreateNewsRequest(CreateNewsScheduledRequest request) throws MyEntityNotFoundException {
        return CreateNewsRequest.builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .immagine(request.immagine())
                .creatorId(request.creatorId())
                .build();
    }

    public NewsResponse toNewsResponse(News request) throws MyEntityNotFoundException {
        return NewsResponse.builder()
                .id(request.getId())
                .titolo(request.getTitolo())
                .contenuto(request.getContenuto())
                .immagine(request.getImmagine())
                .creatorId(request.getCreatorId().getId())
                .build();
    }

    public NewsScheduledResponse toNewsScheduledResponse(NewsScheduled request) throws MyEntityNotFoundException {
        return NewsScheduledResponse.builder()
                .id(request.getId())
                .titolo(request.getTitolo())
                .contenuto(request.getContenuto())
                .immagine(request.getImmagine())
                .publishTime(request.getPublishTime())
                .creatorId(request.getCreatorId().getId())
                .build();
    }
}
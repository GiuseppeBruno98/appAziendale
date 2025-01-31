package com.example.AppAziendale.mappers;

import com.example.AppAziendale.domains.Entities.CommentoELike;
import com.example.AppAziendale.domains.dto.requests.CommentoELikeRequest;
import com.example.AppAziendale.domains.dto.requests.CommentoRequest;
import com.example.AppAziendale.domains.dto.requests.LikeRequest;
import com.example.AppAziendale.domains.dto.responses.CommentoELikeResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.NewsService;
import com.example.AppAziendale.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentoELikeMapper {

    @Autowired
    UtenteService utenteService;

    @Autowired
    NewsService newsService;

    public CommentoELike fromCommentoRequest(CommentoRequest request) throws MyEntityNotFoundException {
        return CommentoELike
                .builder()
                .commento(request.commento())
                .miPiace(false)
                .creatorId(utenteService.getById(request.creatorId().id()))
                .newsId(newsService.getById(request.newsId().id()))
                .build();
    }

    public CommentoELike fromLikeRequest(LikeRequest request) throws MyEntityNotFoundException {
        return CommentoELike
                .builder()
                .miPiace(true)
                .creatorId(utenteService.getById(request.creatorId().id()))
                .newsId(newsService.getById(request.newsId().id()))
                .build();
    }

    public CommentoELike fromCommentoELikeRequest(CommentoELikeRequest request) throws MyEntityNotFoundException {
        return CommentoELike
                .builder()
                .miPiace(true)
                .commento(request.commento())
                .creatorId(utenteService.getById(request.creatorId().id()))
                .newsId(newsService.getById(request.newsId().id()))
                .build();
    }

    public CommentoELikeResponse toCommentoELikeResponse(CommentoELike request) throws MyEntityNotFoundException {

        return CommentoELikeResponse
                .builder()
                .id(request.getId())
                .commento(request.getCommento())
                .miPiace(request.getMiPiace())
                .newsId(request.getNewsId().getId())
                .userId(request.getCreatorId().getId())
                .build();
    }
}

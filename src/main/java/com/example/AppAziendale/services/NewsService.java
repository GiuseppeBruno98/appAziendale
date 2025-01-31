package com.example.AppAziendale.services;

import com.example.AppAziendale.domains.Entities.News;
import com.example.AppAziendale.domains.Entities.Utente;
import com.example.AppAziendale.domains.dto.requests.CreateNewsRequest;
import com.example.AppAziendale.domains.dto.requests.UpdateNewsRequest;
import com.example.AppAziendale.domains.dto.responses.EntityIdResponse;
import com.example.AppAziendale.domains.dto.responses.NewsResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.mappers.NewsMapper;
import com.example.AppAziendale.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private UtenteService utenteService;

    public News getById(Long id) throws MyEntityNotFoundException {
        return newsRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("News con id " + id + " non trovata"));
    }

    public NewsResponse getByIdWithResponse(Long id) throws MyEntityNotFoundException {
        return newsMapper.toNewsResponse(newsRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("News con id " + id + " non esiste!")));
    }

    public List<News> getAll() {
        return newsRepository.findAll();
    }

    public List<NewsResponse> getAllWithResponse() {
        return newsRepository.findAll()
                .stream()
                .map(id -> {
                    try {
                        return newsMapper.toNewsResponse(id);
                    } catch (MyEntityNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }


    public EntityIdResponse createNews(CreateNewsRequest request) throws MyEntityNotFoundException, IllegalAccessException {
        Utente utente = utenteService.getById(request.creatorId().id());
        if (!utente.getIdPosizioneLavorativa().getNome().equals("publisher")) {
            throw new IllegalAccessException("Solo i publisher possono creare le news");
        }
        News savedNews = newsRepository.save(newsMapper.fromCreateNewsRequest(request));
        return new EntityIdResponse(savedNews.getId());
    }

    public EntityIdResponse updateNews(Long id, UpdateNewsRequest request) throws MyEntityNotFoundException {
        News news = getById(id);
        if (request.titolo() != null) news.setTitolo(request.titolo());
        if (request.contenuto() != null) news.setContenuto(request.contenuto());
        if (request.immagine() != null) news.setImmagine(request.immagine());
        return new EntityIdResponse(newsRepository.save(news).getId());
    }

    public void deleteById(Long id) throws MyEntityNotFoundException {
        if (!newsRepository.existsById(id)) {
            throw new MyEntityNotFoundException("News con id " + id + " non trovata");
        }
        newsRepository.deleteById(id);
    }
}
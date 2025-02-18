package com.example.AppAziendale.controllers;

import com.example.AppAziendale.domains.Entities.News;
import com.example.AppAziendale.domains.dto.requests.CreateNewsRequest;
import com.example.AppAziendale.domains.dto.requests.UpdateNewsRequest;
import com.example.AppAziendale.domains.dto.responses.EntityIdResponse;
import com.example.AppAziendale.domains.dto.responses.GenericResponse;
import com.example.AppAziendale.domains.dto.responses.NewsResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/get/{id}")
    public ResponseEntity<News> getById(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(newsService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getResponse/{id}")
    public ResponseEntity<NewsResponse> getByIdWithResponse(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(newsService.getByIdWithResponse(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<News>> getAll() {
        return new ResponseEntity<>(newsService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/allResponse")
    public ResponseEntity<List<NewsResponse>> getAllWithResponse() {
        return new ResponseEntity<>(newsService.getAllWithResponse(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> create(@RequestBody @Valid CreateNewsRequest request) throws MyEntityNotFoundException, IllegalAccessException {
        return new ResponseEntity<>(newsService.createNews(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateNewsRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(newsService.updateNews(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) throws MyEntityNotFoundException {
        newsService.deleteById(id);
        return new ResponseEntity<>(
                new GenericResponse("News con id " + id + " eliminata correttamente"), HttpStatus.OK);
    }
}
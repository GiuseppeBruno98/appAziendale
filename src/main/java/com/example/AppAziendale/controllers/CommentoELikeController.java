package com.example.AppAziendale.controllers;

import com.example.AppAziendale.domains.Entities.CommentoELike;
import com.example.AppAziendale.domains.dto.requests.*;
import com.example.AppAziendale.domains.dto.responses.CommentoELikeResponse;
import com.example.AppAziendale.domains.dto.responses.EntityIdResponse;
import com.example.AppAziendale.domains.dto.responses.GenericResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.CommentoELikeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/commentoELike")
public class CommentoELikeController {

    @Autowired
    private CommentoELikeService commentoELikeService;

    @GetMapping("/get/{id}")
    public ResponseEntity<CommentoELike> getById(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getResponse/{id}")
    public ResponseEntity<CommentoELikeResponse> getByIdWithResponse(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.getByIdWithResponse(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentoELike>> getAll() {
        return new ResponseEntity<>(commentoELikeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/allResponse")
    public ResponseEntity<List<CommentoELikeResponse>> getAllwithResponse() {
        return new ResponseEntity<>(commentoELikeService.getAllWithResponse(), HttpStatus.OK);
    }

    @PostMapping("/createCommento")
    public ResponseEntity<EntityIdResponse> createCommento(@RequestBody @Valid CommentoRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.createCommento(request), HttpStatus.CREATED);
    }

    @PostMapping("/createLike")
    public ResponseEntity<EntityIdResponse> createLike(@RequestBody @Valid LikeRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.createLike(request), HttpStatus.CREATED);
    }

    @PostMapping("/createCommentoELike")
    public ResponseEntity<EntityIdResponse> createCommentoELike(@RequestBody @Valid CommentoELikeRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.createCommentoELike(request), HttpStatus.CREATED);
    }

    @PutMapping("/updateCommentoAdmin/{id}")
    public ResponseEntity<EntityIdResponse> updateCommento(@PathVariable Long id, @RequestBody @Valid UpdateCommentoELikeRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.updateCommento(id, request), HttpStatus.OK);
    }

    @PutMapping("/updateLikeAdmin/{id}")
    public ResponseEntity<EntityIdResponse> updateLike(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.updateLike(id), HttpStatus.OK);
    }

    @PutMapping("/updateCommentoELikeAdmin/{id}")
    public ResponseEntity<EntityIdResponse> updateCommentoELike(@PathVariable Long id, @RequestBody @Valid UpdateCommentoELikeRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.updateCommentoELike(id, request), HttpStatus.OK);
    }

    @PutMapping("/updateCommento")
    public ResponseEntity<EntityIdResponse> updateCommentoByIdUtenteAndIdNews(@RequestBody @Valid UpdateCommentoELikeByIdUtenteAndIdNewsRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.updateCommentoByIdUtenteAndIdNews(request.idCreator().id(), request.idNews().id(), request.req()), HttpStatus.OK);
    }

    @PutMapping("/updateLike")
    public ResponseEntity<EntityIdResponse> updateLikeByIdUtenteAndIdNews(@RequestBody @Valid UtenteAndNewsRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.updateLikeByIdUtenteAndIdNews(request.idCreator().id(), request.idNews().id()), HttpStatus.OK);
    }

    @PutMapping("/updateCommentoELike")
    public ResponseEntity<EntityIdResponse> updateCommentoELikeByIdUtenteAndIdNews(@RequestBody @Valid UpdateCommentoELikeByIdUtenteAndIdNewsRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(commentoELikeService.updateCommentoELikeByIdUtenteAndIdNews(request.idCreator().id(), request.idNews().id(), request.req()), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) throws MyEntityNotFoundException {
        commentoELikeService.deleteById(id);
        return new ResponseEntity<>(
                new GenericResponse("CommentoELike con id " + id + " eliminata correttamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GenericResponse> deleteByIdUtenteAndIdNews(@RequestBody @Valid UtenteAndNewsRequest request) throws MyEntityNotFoundException {
        commentoELikeService.deleteByIdUtenteAndIdNews(request.idCreator().id(), request.idNews().id());
        return new ResponseEntity<>(
                new GenericResponse("CommentoELike con idCreator " + request.idCreator().id() + " e NewsId " + request.idNews().id() + " eliminata correttamente"), HttpStatus.OK);
    }

}

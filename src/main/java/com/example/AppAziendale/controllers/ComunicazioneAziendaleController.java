package com.example.AppAziendale.controllers;

import com.example.AppAziendale.domains.Entities.ComunicazioneAziendale;
import com.example.AppAziendale.domains.dto.requests.CreateComunicazioneAziendaleRequest;
import com.example.AppAziendale.domains.dto.requests.UpdateComunicazioneAziendaleRequest;
import com.example.AppAziendale.domains.dto.responses.ComunicazioneAziendaleResponse;
import com.example.AppAziendale.domains.dto.responses.EntityIdResponse;
import com.example.AppAziendale.domains.dto.responses.GenericResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.ComunicazioneAziendaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/comunicazioneAziendale")
public class ComunicazioneAziendaleController {

    @Autowired
    private ComunicazioneAziendaleService comunicazioneAziendaleService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ComunicazioneAziendale> getById(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(comunicazioneAziendaleService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getResponse/{id}")
    public ResponseEntity<ComunicazioneAziendaleResponse> getByIdWithResponse(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(comunicazioneAziendaleService.getByIdWithResponse(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComunicazioneAziendale>> getAll() {
        return new ResponseEntity<>(comunicazioneAziendaleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/allResponse")
    public ResponseEntity<List<ComunicazioneAziendaleResponse>> getAllwithResponse() {
        return new ResponseEntity<>(comunicazioneAziendaleService.getAllWithResponse(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> create(@RequestBody @Valid CreateComunicazioneAziendaleRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(comunicazioneAziendaleService.createComunicazione(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateComunicazioneAziendaleRequest request) throws MyEntityNotFoundException {
        return new ResponseEntity<>(comunicazioneAziendaleService.updateComunicazione(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) throws MyEntityNotFoundException {
        comunicazioneAziendaleService.deleteById(id);
        return new ResponseEntity<>(
                new GenericResponse("ComunicazioneAziendale con id " + id + " eliminata correttamente"), HttpStatus.OK);
    }
}
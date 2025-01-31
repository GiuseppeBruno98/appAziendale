package com.example.AppAziendale.services;

import com.example.AppAziendale.domains.Entities.ComunicazioneAziendale;
import com.example.AppAziendale.domains.dto.requests.CreateComunicazioneAziendaleRequest;
import com.example.AppAziendale.domains.dto.requests.UpdateComunicazioneAziendaleRequest;
import com.example.AppAziendale.domains.dto.responses.ComunicazioneAziendaleResponse;
import com.example.AppAziendale.domains.dto.responses.EntityIdResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.mappers.ComunicazioneAziendaleMapper;
import com.example.AppAziendale.repositories.ComunicazioneAziendaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunicazioneAziendaleService {

    @Autowired
    private ComunicazioneAziendaleRepository comunicazioneAziendaleRepository;

    @Autowired
    private ComunicazioneAziendaleMapper comunicazioneAziendaleMapper;

    public ComunicazioneAziendale getById(Long id) throws MyEntityNotFoundException {
        return comunicazioneAziendaleRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Comunicazione con id " + id + " non trovata"));
    }

    public ComunicazioneAziendaleResponse getByIdWithResponse(Long id) throws MyEntityNotFoundException {
        return comunicazioneAziendaleMapper.toComunicazioneAziendaleResponse(comunicazioneAziendaleRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("l'utente con id " + id + " non esiste!")));
    }

    public List<ComunicazioneAziendale> getAll() {
        return comunicazioneAziendaleRepository.findAll();
    }

    public List<ComunicazioneAziendaleResponse> getAllWithResponse() {
        return comunicazioneAziendaleRepository.findAll()
                .stream()
                .map(id -> {
                    try {
                        return comunicazioneAziendaleMapper.toComunicazioneAziendaleResponse(id);
                    } catch (MyEntityNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public EntityIdResponse createComunicazione(CreateComunicazioneAziendaleRequest request) throws MyEntityNotFoundException {
        ComunicazioneAziendale savedComunicazione = comunicazioneAziendaleRepository.save(comunicazioneAziendaleMapper.fromCreateComunicazioneAziendaleRequest(request));
        return new EntityIdResponse(savedComunicazione.getId());
    }

    public EntityIdResponse updateComunicazione(Long id, UpdateComunicazioneAziendaleRequest request) throws MyEntityNotFoundException {
        ComunicazioneAziendale comunicazione = getById(id);
        if (request.titolo() != null) comunicazione.setTitolo(request.titolo());
        if (request.contenuto() != null) comunicazione.setContenuto(request.contenuto());
        return new EntityIdResponse(comunicazioneAziendaleRepository.save(comunicazione).getId());
    }

    public void deleteById(Long id) throws MyEntityNotFoundException {
        if (!comunicazioneAziendaleRepository.existsById(id)) {
            throw new MyEntityNotFoundException("Comunicazione con id " + id + " non trovata");
        }
        comunicazioneAziendaleRepository.deleteById(id);
    }
}
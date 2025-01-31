package com.example.AppAziendale.mappers;

import com.example.AppAziendale.domains.Entities.Utente;
import com.example.AppAziendale.domains.dto.requests.CreateUtenteRequest;
import com.example.AppAziendale.domains.dto.responses.UtenteResponse;
import com.example.AppAziendale.domains.enums.Role;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.services.PosizioneLavorativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteMapper {

    @Autowired
    PosizioneLavorativaService posizioneLavorativaService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Utente fromCreateUtenteRequest(CreateUtenteRequest request) throws MyEntityNotFoundException {
        return Utente
                .builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .dataNascita(request.dataNascita())
                .luogoNascita(request.luogoNascita())
                .telefono(request.telefono())
                .indirizzo(request.indirizzo())
                .ruolo(Role.UTENTE)
                .imgUtente(request.imgUtente())
                .idPosizioneLavorativa(posizioneLavorativaService.getById(request.idPosizioneLavorativa().id()))
                .build();
    }

    public UtenteResponse toUtenteResponse(Utente request) throws MyEntityNotFoundException {
        return UtenteResponse
                .builder()
                .id(request.getId())
                .nome(request.getNome())
                .cognome(request.getCognome())
                .email(request.getEmail())
                .password(request.getPassword())
                .dataNascita(request.getDataNascita())
                .luogoNascita(request.getLuogoNascita())
                .telefono(request.getTelefono())
                .indirizzo(request.getIndirizzo())
                .ruolo(request.getRuolo().name())
                .imgUtente(request.getImgUtente())
                .idPosizioneLavorativa(request.getIdPosizioneLavorativa().getId())
                .build();
    }


}

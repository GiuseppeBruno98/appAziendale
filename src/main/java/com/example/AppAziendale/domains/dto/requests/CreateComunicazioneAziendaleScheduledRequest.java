package com.example.AppAziendale.domains.dto.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateComunicazioneAziendaleScheduledRequest(
        @NotBlank(message = "Il titolo non può essere blank o null")
        String titolo,

        @NotBlank(message = "Il contenuto non può essere blank o null")
        String contenuto,

        @NotNull(message = "L'id del creatore deve essere presente")
        EntityIdRequest creatorId,

        @Future
        LocalDateTime publishTime
) {

}

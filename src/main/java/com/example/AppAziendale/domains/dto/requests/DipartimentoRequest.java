package com.example.AppAziendale.domains.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record DipartimentoRequest(
        @NotBlank(message = "Il nome non può essere blank o null")
        String nome,

        @NotBlank(message = "La descrizione non può essere blank o null")
        String descrizione
) {

}

package com.example.AppAziendale.domains.dto.requests;

import com.example.AppAziendale.domains.dto.responses.EntityIdResponse;
import jakarta.validation.constraints.NotNull;

public record CreateTimbriRequest(

        @NotNull(message = "L'utente deve essere presente")
        EntityIdResponse utenteId

) {

}

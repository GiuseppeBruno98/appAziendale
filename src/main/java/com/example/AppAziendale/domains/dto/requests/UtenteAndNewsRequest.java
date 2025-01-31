package com.example.AppAziendale.domains.dto.requests;

public record UtenteAndNewsRequest(
        EntityIdRequest idCreator,
        EntityIdRequest idNews
) {
}

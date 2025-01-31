package com.example.AppAziendale.domains.dto.requests;

import jakarta.validation.constraints.NotNull;

public record UpdateCommentoELikeByIdUtenteAndIdNewsRequest(
        @NotNull(message = "il creatorId non deve essere null")
        EntityIdRequest idCreator,
        @NotNull(message = "il newsId non deve essere null")
        EntityIdRequest idNews,
        @NotNull(message = "la request non deve essere null")
        UpdateCommentoELikeRequest req
) {
}

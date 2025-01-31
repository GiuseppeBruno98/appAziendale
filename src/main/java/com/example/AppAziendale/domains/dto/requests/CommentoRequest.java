package com.example.AppAziendale.domains.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentoRequest(
        @NotBlank(message = "il commento non deve essere blank o null")
        String commento,

        @NotNull(message = "L'id del creatore deve essere presente")
        EntityIdRequest creatorId,

        @NotNull(message = "L'id della news deve essere presente")
        EntityIdRequest newsId
) {
}

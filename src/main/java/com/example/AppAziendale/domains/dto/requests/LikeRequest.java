package com.example.AppAziendale.domains.dto.requests;

import jakarta.validation.constraints.NotNull;

public record LikeRequest(

        @NotNull(message = "L'id del creatore deve essere presente")
        EntityIdRequest creatorId,

        @NotNull(message = "L'id della news deve essere presente")
        EntityIdRequest newsId
) {
}

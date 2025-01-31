package com.example.AppAziendale.domains.dto.responses;

import lombok.Builder;

@Builder
public record NewsResponse(
        Long id,
        String titolo,
        String contenuto,
        String immagine,
        Long creatorId
) {
}
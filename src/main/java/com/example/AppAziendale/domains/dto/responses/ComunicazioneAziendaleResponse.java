package com.example.AppAziendale.domains.dto.responses;

import lombok.Builder;


@Builder
public record ComunicazioneAziendaleResponse(
        Long id,
        String titolo,
        String contenuto,
        Long creatorId
) {
}

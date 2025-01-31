package com.example.AppAziendale.domains.dto.responses;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ComunicazioneAziendaleScheduledResponse(
        Long id,
        String titolo,
        String contenuto,
        Long creatorId,
        LocalDateTime publishTime
) {
}

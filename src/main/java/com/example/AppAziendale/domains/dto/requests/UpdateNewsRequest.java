package com.example.AppAziendale.domains.dto.requests;

public record UpdateNewsRequest(
        String titolo,

        String contenuto,

        String immagine
) {
}

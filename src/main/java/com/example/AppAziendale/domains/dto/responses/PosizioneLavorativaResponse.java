package com.example.AppAziendale.domains.dto.responses;

import lombok.Builder;

@Builder
public record PosizioneLavorativaResponse(Long id,
                                          String nome,
                                          String descrizione,
                                          Long idDipartimento
) {

}
package com.example.AppAziendale.domains.dto.responses;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {
}

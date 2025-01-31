package com.example.AppAziendale.services;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        // Simulazione di un utente autenticato
        // In un'applicazione reale, puoi recuperarlo da SecurityContextHolder
        return Optional.of(1L); // Supponiamo che l'utente con ID 1 sia loggato
    }
}

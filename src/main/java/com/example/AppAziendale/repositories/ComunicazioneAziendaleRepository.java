package com.example.AppAziendale.repositories;

import com.example.AppAziendale.domains.Entities.ComunicazioneAziendale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicazioneAziendaleRepository extends JpaRepository<ComunicazioneAziendale, Long> {
}

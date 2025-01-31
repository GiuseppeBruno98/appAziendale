package com.example.AppAziendale.repositories;

import com.example.AppAziendale.domains.Entities.ComunicazioneAziendaleScheduled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicazioneAziendaleScheduledRepository extends JpaRepository<ComunicazioneAziendaleScheduled, Long> {
}

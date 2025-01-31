package com.example.AppAziendale.repositories;

import com.example.AppAziendale.domains.Entities.Timbri;
import com.example.AppAziendale.domains.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimbriRepository extends JpaRepository<Timbri, Long> {

    List<Timbri> findByUtenteId(Utente utenteId);
}

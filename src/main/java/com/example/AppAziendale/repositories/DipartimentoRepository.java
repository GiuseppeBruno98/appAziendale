package com.example.AppAziendale.repositories;

import com.example.AppAziendale.domains.Entities.Dipartimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipartimentoRepository extends JpaRepository<Dipartimento, Long> {
}

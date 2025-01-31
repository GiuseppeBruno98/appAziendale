package com.example.AppAziendale.repositories;

import com.example.AppAziendale.domains.Entities.NewsScheduled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsScheduledRepository extends JpaRepository<NewsScheduled, Long> {
}

package com.example.AppAziendale.repositories;

import com.example.AppAziendale.domains.Entities.CommentoELike;
import com.example.AppAziendale.domains.Entities.News;
import com.example.AppAziendale.domains.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentoELikeRepository extends JpaRepository<CommentoELike, Long> {

    List<CommentoELike> findByNewsId(News news);

    List<CommentoELike> findByCreatorId(Utente utente);

    Optional<CommentoELike> findByCreatorIdAndNewsId(Utente utente, News news);

}

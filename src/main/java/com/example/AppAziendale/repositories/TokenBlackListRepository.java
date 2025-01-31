package com.example.AppAziendale.repositories;

import com.example.AppAziendale.domains.Entities.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

    Optional<TokenBlackList> getByToken(String token);

}

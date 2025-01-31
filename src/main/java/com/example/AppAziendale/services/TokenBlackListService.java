package com.example.AppAziendale.services;

import com.example.AppAziendale.domains.Entities.TokenBlackList;
import com.example.AppAziendale.repositories.TokenBlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService {

    @Autowired
    private TokenBlackListRepository tokenBlackListRepository;
    @Autowired
    private UtenteService utenteService;

    public Boolean isPresentToken(String token) {
        return tokenBlackListRepository.getByToken(token).isPresent();
    }

    public void insertToken(Long id_utente, String token) {
        TokenBlackList tokenBlackList = TokenBlackList
                .builder()
                .token(token)
                .utente(utenteService.getById(id_utente))
                .build();
        tokenBlackListRepository.save(tokenBlackList);
    }

}

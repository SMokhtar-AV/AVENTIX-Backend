package com.AventixPay.Aventix.service;

import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public void createCard() {
        Card card = new Card();
        cardRepository.save(card);
    }

}

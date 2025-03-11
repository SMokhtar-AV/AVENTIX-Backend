package com.AventixPay.Aventix.repositories;

import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAll();

    List<Card> findCardsByEnterpriseId(Long enterpriseId);

    ResponseEntity<Card> deleteByID(Long id);

    Card findByID(Long id);

    Optional<Card> findByCardNumber(String cardNumber);

    Card findByUserId(Optional<User> cardOwner);
}

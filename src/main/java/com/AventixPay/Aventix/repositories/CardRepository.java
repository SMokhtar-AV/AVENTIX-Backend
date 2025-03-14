package com.AventixPay.Aventix.repositories;

import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAll();

    List<Card> findCardsByEnterpriseId(Long enterpriseId);

    void deleteById(Long id);

    Optional<Card> findById(Long id);

    Optional<Card> findByCardNumber(String cardNumber);

    Card findByUserId(Long userId);
}

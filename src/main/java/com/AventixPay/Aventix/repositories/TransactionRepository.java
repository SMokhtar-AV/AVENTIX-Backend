package com.AventixPay.Aventix.repositories;


import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

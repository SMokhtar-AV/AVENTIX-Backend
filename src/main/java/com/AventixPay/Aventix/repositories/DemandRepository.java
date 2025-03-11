package com.AventixPay.Aventix.repositories;


import com.AventixPay.Aventix.entities.Demand;
import com.AventixPay.Aventix.enumClass.DemandeEtat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {


    List<Demand> findByEtat(DemandeEtat nonValide);
}

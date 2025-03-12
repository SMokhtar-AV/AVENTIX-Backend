package com.AventixPay.Aventix.repositories;


import com.AventixPay.Aventix.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findAllByCommercialId(Long userId);

    List<Facture> findAllByEmployeeId(Long userId);
}

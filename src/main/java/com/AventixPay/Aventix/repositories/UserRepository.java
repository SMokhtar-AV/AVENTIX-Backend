package com.AventixPay.Aventix.repositories;


import com.AventixPay.Aventix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEntrepriseId(Long entrepriseId);
}

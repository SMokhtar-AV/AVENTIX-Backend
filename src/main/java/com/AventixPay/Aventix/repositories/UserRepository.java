package com.AventixPay.Aventix.repositories;


import com.AventixPay.Aventix.entities.Entreprise;
import com.AventixPay.Aventix.entities.Roles;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.enumClass.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByRole(Roles role);
    Optional<User> findByEmail(String email);
    List<User> findByEntrepriseAndEmailNot(Entreprise entreprise, String email);
    List<User> findByEntrepriseId(Long entrepriseId);
    List<User> findAll();
}

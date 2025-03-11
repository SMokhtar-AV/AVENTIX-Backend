package com.AventixPay.Aventix.repositories;

import com.AventixPay.Aventix.entities.Roles;
import com.AventixPay.Aventix.enumClass.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    List<Roles> findAll();
    Optional<Roles> findByRole(Role name);
}

package com.AventixPay.Aventix.service;


import com.AventixPay.Aventix.entities.Entreprise;
import com.AventixPay.Aventix.entities.Roles;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.enumClass.Role;
import com.AventixPay.Aventix.repositories.RoleRepository;
import com.AventixPay.Aventix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    KeycloakService keycloakService;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user) {

        keycloakService.createUser(user);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setPhone(userDetails.getPhone());
        user.setSolde(userDetails.getSolde());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserByRole(Roles role) {

        User user = userRepository.findByRole(role).get();
        return user;
    }

    public List<User> getUsersByEntrepriseExcludingEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Entreprise entreprise = user.getEntreprise();
            if (entreprise != null) {
                return userRepository.findByEntrepriseAndEmailNot(entreprise, email);
            }
        }
        return Collections.emptyList();
    }

    public User getUserByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user;
        }
        return null;
    }

}

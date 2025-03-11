package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.Roles;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.repositories.RoleRepository;
import com.AventixPay.Aventix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-email")
    public ResponseEntity<List<User>> getUsersByEntreprise(@RequestParam String email) {
        List<User> users = userService.getUsersByEntrepriseExcludingEmail(email);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/roles/all")
    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> roles = roleRepository.findAll();

        return ResponseEntity.ok(roles);
    }

}

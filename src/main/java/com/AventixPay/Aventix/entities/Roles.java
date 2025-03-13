package com.AventixPay.Aventix.entities;

import com.AventixPay.Aventix.enumClass.Role;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public List<User> getUser() {
        return user;
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private List<User> user;



}

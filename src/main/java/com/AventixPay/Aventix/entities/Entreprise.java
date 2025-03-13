package com.AventixPay.Aventix.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "entreprise")
    @JsonManagedReference("user-entreprise")
    private List<User> listUser;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getListUser() {
        return listUser;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }
}

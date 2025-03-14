package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.Facture;
import com.AventixPay.Aventix.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/facture")
public class FactureController {
    @Autowired
    private FactureService factureService;

    @PostMapping("/addFacture")
    public ResponseEntity<Facture> addFacture(@RequestBody Facture facture) {
        Facture savedFacture = factureService.saveFacture(facture);
        return new ResponseEntity<>(savedFacture, HttpStatus.CREATED);
    }
}


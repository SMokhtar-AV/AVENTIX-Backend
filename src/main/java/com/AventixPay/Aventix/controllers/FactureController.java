package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.Facture;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.repositories.FactureRepository;
import com.AventixPay.Aventix.service.FactureService;
import com.AventixPay.Aventix.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/api/facture")

public class FactureController {

    private final PaymentService paymentService;

    private final FactureRepository factureRepository;

    public FactureController(PaymentService paymentService, FactureRepository factureRepository) {
        this.paymentService = paymentService;
        this.factureRepository = factureRepository;
    }

    @GetMapping("/factures-commercial")

    public List<Facture> getFacturesByCommercialId(Long idCommercial){
        //User commercial = paymentService.getAuthenticatedUser();

        //Long id_commercial = commercial.getId();
        Long id_commercial = 23L;

        return factureRepository.findAllByCommercialId(id_commercial);
    }

    @GetMapping("/factures-employee")
   // @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public List<Facture> getFacturesByEmployeeId(Long idEmployee){
        User employee = new User();

        Long id_employee = employee.getId();

        return factureRepository.findAllByCommercialId(id_employee);
    }

}

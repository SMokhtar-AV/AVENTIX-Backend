package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.Facture;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.repositories.FactureRepository;
import com.AventixPay.Aventix.service.FactureService;
import com.AventixPay.Aventix.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("hasAuthority('ROLE_COMMERCIAL')")
    public List<Facture> getFacturesByCommercialId(Long idCommercial){
        User commercial = paymentService.getAuthenticatedUser();

        Long id_commercial = commercial.getId();

        return factureRepository.findAllByCommercialId(id_commercial);
    }

    @GetMapping("/factures-employee")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public List<Facture> getFacturesByEmployeeId(Long idEmployee){
        User employee = paymentService.getAuthenticatedUser();

        Long id_employee = employee.getId();

        return factureRepository.findAllByCommercialId(id_employee);
    }

}

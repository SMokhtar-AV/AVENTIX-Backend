package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.DTO.PaymentRequest;
import com.AventixPay.Aventix.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/payment")

public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
   // @PreAuthorize("hasAuthority('ROLE_COMMERCIAL')")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest, Long userId) {
        try {
            //paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok(paymentService.processPayment(paymentRequest, userId));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.DTO.PaymentRequest;
import com.AventixPay.Aventix.service.PaymentService;

import com.AventixPay.Aventix.service.RFIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    private RFIDService rfidService;

    @PostMapping("/process")
   // @PreAuthorize("hasAuthority('ROLE_COMMERCIAL')")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            //paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok(rfidService.readSerialNumberFromRFID(paymentRequest));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

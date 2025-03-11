package com.AventixPay.Aventix.DTO;


import lombok.Data;

@Data
public class PaymentRequest {
    private String cardNumber;
    private double montant;
}

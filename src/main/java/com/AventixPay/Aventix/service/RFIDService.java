package com.AventixPay.Aventix.service;

import com.AventixPay.Aventix.DTO.PaymentRequest;

public interface RFIDService {


    String readSerialNumberFromRFID(PaymentRequest paymentRequest);
}

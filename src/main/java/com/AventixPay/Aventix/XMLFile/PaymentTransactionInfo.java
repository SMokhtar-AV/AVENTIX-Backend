package com.AventixPay.Aventix.XMLFile;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;

//@XmlRootElement
@Data
public class PaymentTransactionInfo {

    private Long transactionId;
    private double montant;
    private LocalDateTime dateTransaction;
    private String statutTransaction;

    private String cardNumber;
    private LocalDate validityDate;
    private String cardStatut;

    private String ownerFirstName;
    private String ownerLastName;

    private Long commercialId;
    private String commercialFirstName;
    private String commercialLastName;

    public PaymentTransactionInfo(Long transactionId,
                                  double montant,
                                  LocalDateTime dateTransaction,
                                  String statutTransaction,
                                  String cardNumber,
                                  LocalDate validityDate,
                                  String cardStatut,
                                  String ownerFirstName,
                                  String ownerLastName,
                                  Long commercialId,
                                  String commercialFirstName,
                                  String commercialLastName) {
        this.transactionId = transactionId;
        this.montant = montant;
        this.dateTransaction = dateTransaction;
        this.statutTransaction = statutTransaction;
        this.cardNumber = cardNumber;
        this.validityDate = validityDate;
        this.cardStatut = cardStatut;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.commercialId = commercialId;
        this.commercialFirstName = commercialFirstName;
        this.commercialLastName = commercialLastName;
    }

    @XmlElement
    public Long getTransactionId() {
        return transactionId;
    }

    @XmlElement
    public double getMontant() {
        return montant;
    }

    @XmlElement
    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    @XmlElement
    public String getStatutTransaction() {
        return statutTransaction;
    }

    @XmlElement
    public String getCardNumber() {
        return cardNumber;
    }

    @XmlElement
    public LocalDate getValidityDate() {
        return validityDate;
    }

    @XmlElement
    public String getCardStatut() {
        return cardStatut;
    }

    @XmlElement
    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    @XmlElement
    public String getOwnerLastName() {
        return ownerLastName;
    }

    @XmlElement
    public Long getCommercialId() {
        return commercialId;
    }

    @XmlElement
    public String getCommercialFirstName() {
        return commercialFirstName;
    }

    @XmlElement
    public String getCommercialLastName() {
        return commercialLastName;
    }
}

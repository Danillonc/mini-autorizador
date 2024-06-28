package com.mini.autorizador.domain.entity;

import com.mini.autorizador.domain.repository.CreditCardRepository;
import jakarta.persistence.*;

/**
 * This class represents the domain class level.
 */
@Entity
@Table(name = "CreditCard")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cardPassword")
    private String cardPassword;

    @Column(name = "Amount")
    private double amount;

    public CreditCard(){}

    public CreditCard(String cardNumber, String cardPassword) {
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.amount = 500.00;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

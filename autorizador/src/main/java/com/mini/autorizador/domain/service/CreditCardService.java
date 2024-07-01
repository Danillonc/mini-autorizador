package com.mini.autorizador.domain.service;

import com.mini.autorizador.domain.entity.CreditCard;

import java.util.Optional;

/**
 * Interface responsible to define credit card business contracts.
 */
public interface CreditCardService {

    CreditCard createCreditCard(CreditCard creditCard);
    CreditCard findByCreditCardNumber(String creditCardNumber);
    CreditCard findByCreditCardNumberWithLock(String creditCardNumber);
}

package com.mini.autorizador.domain.service;

import com.mini.autorizador.domain.entity.CreditCard;

/**
 * Interface responsible to define credit card business contracts.
 */
public interface CreditCardService {

    CreditCard createCreditCard(CreditCard creditCard);
}

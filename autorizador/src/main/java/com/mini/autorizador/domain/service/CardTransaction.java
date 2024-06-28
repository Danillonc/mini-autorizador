package com.mini.autorizador.domain.service;

import com.mini.autorizador.domain.entity.CreditCard;

public interface CardTransaction {

    void createTransaction(CreditCard creditCard);
}

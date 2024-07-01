package com.mini.autorizador.domain.service;

import com.mini.autorizador.domain.entity.CreditCard;

public interface CardTransactionService {

    void createTransaction(CreditCard creditCard);
}

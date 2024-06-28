package com.mini.autorizador.domain.service.impl;

import com.mini.autorizador.domain.entity.CreditCard;
import com.mini.autorizador.domain.service.CardTransaction;
import com.mini.autorizador.domain.service.CreditCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardTransactionImpl implements CardTransaction {

    private transient CreditCardService creditCardService;

    public CardTransactionImpl(final CreditCardService service){
        this.creditCardService = service;
    }

    @Transactional
    @Override
    public void createTransaction(CreditCard creditCard) {
      //validar os dados do cartão
      //validar saldo do cartão
      //executar operação
    }
}

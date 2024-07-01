package com.mini.autorizador.domain.service.impl;

import com.mini.autorizador.domain.entity.CreditCard;
import com.mini.autorizador.domain.exception.CreditCardInvalidFundsException;
import com.mini.autorizador.domain.exception.CreditCardInvalidPasswordException;
import com.mini.autorizador.domain.repository.CardTransactionRepository;
import com.mini.autorizador.domain.service.CardTransactionService;
import com.mini.autorizador.domain.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * This class represents an implementation to our transaction business layer.
 */
public class CardTransactionServiceImpl implements CardTransactionService {

    private final transient CreditCardService creditCardService;
    private final transient CardTransactionRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CardTransactionServiceImpl.class);

    public CardTransactionServiceImpl(CreditCardService service, CardTransactionRepository repository){
        this.creditCardService = service;
        this.repository = repository;
    }

    @Transactional
    @Override
    public void createTransaction(CreditCard creditCard) {
        LOGGER.info("Iniciando Transação - CardTransactionServiceImpl.createTransaction");
        CreditCard cardDb = this.creditCardService.findByCreditCardNumberWithLock(creditCard.getCardNumber());
        validateCardPassword(creditCard.getCardPassword(), cardDb.getCardPassword());
        validateCardFunds(creditCard.getAmount(), cardDb.getAmount());
        BigDecimal totalValueOperation = cardDb.getAmount().subtract(creditCard.getAmount());
        cardDb.setAmount(totalValueOperation);
        this.repository.saveAndFlush(cardDb);
        LOGGER.info("Finalizando Transação - CardTransactionServiceImpl.createTransaction");
    }

    private void validateCardPassword(String passwordTransaction, String passwordDb){
        if(!Objects.equals(passwordTransaction, passwordDb)){
            throw new CreditCardInvalidPasswordException();
        }
    }

    private void validateCardFunds(BigDecimal transactionValue, BigDecimal cardAmount){
        if(cardAmount.compareTo(transactionValue) < 0){
            throw new CreditCardInvalidFundsException();
        }
    }
}

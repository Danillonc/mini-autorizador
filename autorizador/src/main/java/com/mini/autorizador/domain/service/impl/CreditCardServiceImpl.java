package com.mini.autorizador.domain.service.impl;

import com.mini.autorizador.domain.entity.CreditCard;
import com.mini.autorizador.domain.exception.CreditCardException;
import com.mini.autorizador.domain.exception.CreditCardNotExistsException;
import com.mini.autorizador.domain.repository.CreditCardRepository;
import com.mini.autorizador.domain.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class represents an implementation to our credit cass business layer.
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final transient CreditCardRepository creditCardRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    public CreditCardServiceImpl(CreditCardRepository repository){
        this.creditCardRepository = repository;
    }

    @Transactional
    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        LOGGER.info("Iniciando a criação do cartão - CreditCardServiceImpl.createCreditCard");
        verifyCreditCard(creditCard.getCardNumber());
        LOGGER.info("Finalizando a criação do cartão - CreditCardServiceImpl.createCreditCard");
        return this.creditCardRepository.saveAndFlush(creditCard);
    }

    @Override
    public CreditCard findByCreditCardNumber(String creditCardNumber) {
        return this.creditCardRepository.findByCardNumber(creditCardNumber)
                .orElseThrow(CreditCardNotExistsException::new);
    }

    @Override
    public CreditCard findByCreditCardNumberWithLock(String creditCardNumber) {
        return this.creditCardRepository.findByCardNumberWithLock(creditCardNumber)
                .orElseThrow(CreditCardNotExistsException::new);
    }

    private void verifyCreditCard(String creditCardId){
        boolean isCardActive = this.creditCardRepository.findByCardNumber(creditCardId).isPresent();
        if(isCardActive){
            throw new CreditCardException();
        }
    }


}

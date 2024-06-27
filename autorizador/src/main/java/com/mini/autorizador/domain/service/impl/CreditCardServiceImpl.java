package com.mini.autorizador.domain.service.impl;

import com.mini.autorizador.domain.entity.CreditCard;
import com.mini.autorizador.domain.repository.CreditCardRepository;
import com.mini.autorizador.domain.service.CreditCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class represents an implementation to our credit cass business layer.
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

    private transient CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository repository){
        this.creditCardRepository = repository;
    }

    @Transactional
    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        return this.creditCardRepository.saveAndFlush(creditCard);
    }

}

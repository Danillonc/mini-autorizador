package com.mini.autorizador.domain.service.impl;

import com.mini.autorizador.domain.entity.CreditCard;
import com.mini.autorizador.domain.exception.CreditCardException;
import com.mini.autorizador.domain.exception.CreditCardNotExistsException;
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
        verifyCreditCard(creditCard.getCardNumber());
        return this.creditCardRepository.saveAndFlush(creditCard);
    }

    @Override
    public CreditCard findByCreditCardNumber(String creditCardNumber) {
        return this.creditCardRepository.findByCardNumber(creditCardNumber)
                .orElseThrow(CreditCardNotExistsException::new);
    }

    private void verifyCreditCard(String creditCardId){
        boolean isCardActive = this.creditCardRepository.findByCardNumber(creditCardId).isPresent();
        if(isCardActive){
            throw new CreditCardException();
        }
    }


}

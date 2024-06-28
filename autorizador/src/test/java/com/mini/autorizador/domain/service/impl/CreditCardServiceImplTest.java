package com.mini.autorizador.domain.service.impl;

import com.mini.autorizador.domain.entity.CreditCard;
import com.mini.autorizador.domain.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreditCardServiceImplTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Test
    public void createCreditCardTest(){
        CreditCard creditCard = new CreditCard("123456789001", "123456");

        when(creditCardRepository.saveAndFlush(Mockito.any())).thenReturn(creditCard);

        CreditCard cardResposne = this.creditCardService.createCreditCard(creditCard);

        assertNotNull(cardResposne);
    }

    @Test
    public void findCreditCardByNumberTest(){
        final String cardNumber = "123456789001";
        CreditCard creditCard = new CreditCard("123456789001", "123456");

        when(creditCardRepository.findByCardNumber(Mockito.anyString())).thenReturn(Optional.of(creditCard));

        assertNotNull(this.creditCardService.findByCreditCardNumber(cardNumber));
    }
}

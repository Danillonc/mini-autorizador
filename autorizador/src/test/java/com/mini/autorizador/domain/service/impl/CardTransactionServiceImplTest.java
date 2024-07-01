package com.mini.autorizador.domain.service.impl;

import com.mini.autorizador.application.request.CardTransactionRecord;
import com.mini.autorizador.domain.entity.CreditCard;
import com.mini.autorizador.domain.exception.CreditCardInvalidFundsException;
import com.mini.autorizador.domain.exception.CreditCardInvalidPasswordException;
import com.mini.autorizador.domain.exception.CreditCardNotExistsException;
import com.mini.autorizador.domain.repository.CardTransactionRepository;
import com.mini.autorizador.util.CardTransactionConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardTransactionServiceImplTest {

    @Mock
    private CardTransactionRepository repository;

    @Mock
    private CreditCardServiceImpl creditCardService;

    @InjectMocks
    private CardTransactionServiceImpl cardTransactionService;


    @Test
    public void createTransactionInvalidCardNumber(){
        CardTransactionRecord cardTransactionInput = new CardTransactionRecord("6549873025634504", "1234", 10.00);

        when(creditCardService.findByCreditCardNumberWithLock(Mockito.anyString())).thenThrow(new CreditCardNotExistsException());

        CreditCardNotExistsException exception = Assertions.assertThrows(CreditCardNotExistsException.class,
                () -> this.cardTransactionService.createTransaction(CardTransactionConverter.convertToDomain(cardTransactionInput)));

        Assertions.assertEquals("Não foi possível consultar o cartão.", exception.getMessage());

    }

    @Test
    public void createTransactionFundsException(){
        CardTransactionRecord cardTransactionInput = new CardTransactionRecord("6549873025634504", "1234", 10.00);
        CreditCard creditCardDb = new CreditCard();
        creditCardDb.setCardNumber("6549873025634503");
        creditCardDb.setCardPassword("1234");
        creditCardDb.setAmount(BigDecimal.valueOf(5.00));

        when(creditCardService.findByCreditCardNumberWithLock(Mockito.anyString())).thenReturn(creditCardDb);

        CreditCardInvalidFundsException exception = Assertions.assertThrows(CreditCardInvalidFundsException.class,
                () -> this.cardTransactionService.createTransaction(CardTransactionConverter.convertToDomain(cardTransactionInput)));

        Assertions.assertEquals("Não foi possível realizar o pagamento.", exception.getMessage());

    }

    @Test
    public void createTransactionInvalidPasswordException(){
        CardTransactionRecord cardTransactionInput = new CardTransactionRecord("6549873025634504", "1233", 10.00);
        CreditCard creditCardDb = new CreditCard();
        creditCardDb.setCardNumber("6549873025634503");
        creditCardDb.setCardPassword("1234");
        creditCardDb.setAmount(BigDecimal.valueOf(5.00));

        when(creditCardService.findByCreditCardNumberWithLock(Mockito.anyString())).thenReturn(creditCardDb);

        CreditCardInvalidPasswordException exception = Assertions.assertThrows(CreditCardInvalidPasswordException.class,
                () -> this.cardTransactionService.createTransaction(CardTransactionConverter.convertToDomain(cardTransactionInput)));

        Assertions.assertEquals("Não foi possível autorizar o cartão.", exception.getMessage());

    }
}

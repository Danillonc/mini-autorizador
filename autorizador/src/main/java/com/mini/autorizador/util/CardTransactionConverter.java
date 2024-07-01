package com.mini.autorizador.util;

import com.mini.autorizador.application.request.CardTransactionRecord;
import com.mini.autorizador.domain.entity.CreditCard;

import java.math.BigDecimal;

public class CardTransactionConverter {

    private CardTransactionConverter(){}

    public static CreditCard convertToDomain(final CardTransactionRecord record){
        CreditCard creditCard = new CreditCard();
        creditCard.setAmount(BigDecimal.valueOf(record.valor()));
        creditCard.setCardPassword(record.senhaCartao());
        creditCard.setCardNumber(record.numeroCartao());
        return creditCard;
    }
}

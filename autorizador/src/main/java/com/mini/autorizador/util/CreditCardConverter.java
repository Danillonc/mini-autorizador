package com.mini.autorizador.util;

import com.mini.autorizador.application.request.CreditCardRecord;
import com.mini.autorizador.domain.entity.CreditCard;

/**
 * Utility class responsible to convert data between domain and dto.
 */
public class CreditCardConverter {

    private CreditCardConverter(){}

    public static CreditCard convertToDomain(final CreditCardRecord card){
        return new CreditCard(card.numeroCartao(), card.senha());
    }

    public static CreditCardRecord convertToDto(final CreditCard cardDomain){
        return null;
    }
}
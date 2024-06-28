package com.mini.autorizador.domain.exception;

public class CreditCardNotExistsException extends  RuntimeException{

    public CreditCardNotExistsException(){
        super("Não foi possível consultar o cartão.", new Throwable("Cartão Inexistente."));
    }
}

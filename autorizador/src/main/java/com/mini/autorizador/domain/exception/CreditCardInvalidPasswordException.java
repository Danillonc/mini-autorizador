package com.mini.autorizador.domain.exception;

public class CreditCardInvalidPasswordException extends RuntimeException{

    public CreditCardInvalidPasswordException(){
        super("Não foi possível autorizar o cartão.", new Throwable("Senha Inválida."));
    }
}

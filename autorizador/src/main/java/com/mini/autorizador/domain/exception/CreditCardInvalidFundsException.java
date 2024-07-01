package com.mini.autorizador.domain.exception;

public class CreditCardInvalidFundsException extends RuntimeException{

    public CreditCardInvalidFundsException(){
        super("Não foi possível realizar o pagamento.", new Throwable("Limite Insuficiente."));
    }
}

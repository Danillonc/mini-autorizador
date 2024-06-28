package com.mini.autorizador.domain.exception;

public class CreditCardException extends RuntimeException{

    public CreditCardException(){
        super("Cartão já existente!");
    }

    public CreditCardException(String msg){
        super(msg);
    }
}

package com.mini.autorizador.application.controller;


import com.mini.autorizador.domain.service.CreditCardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/creditcard")
public class CreditCardController {

    private transient CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService){
        this.creditCardService = creditCardService;
    }

    //criar metodo post para criar o cartão.
}

package com.mini.autorizador.application.controller;

import com.mini.autorizador.application.request.CardTransactionRecord;
import com.mini.autorizador.application.request.CreditCardRecord;
import com.mini.autorizador.domain.service.CardTransactionService;
import com.mini.autorizador.util.CardTransactionConverter;
import com.mini.autorizador.util.CreditCardConverter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CardTransactionController {

    private final transient CardTransactionService cardTransactionService;

    public CardTransactionController(CardTransactionService cardTransactionService){
        this.cardTransactionService = cardTransactionService;
    }

    @PostMapping("/transacoes")
    public ResponseEntity<Void> createCreditCard(@RequestBody @Valid CardTransactionRecord cardTransactipn){
        this.cardTransactionService.createTransaction(CardTransactionConverter.convertToDomain(cardTransactipn));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

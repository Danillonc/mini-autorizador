package com.mini.autorizador.application.controller;


import com.mini.autorizador.application.request.CreditCardRecord;
import com.mini.autorizador.domain.service.CreditCardService;
import com.mini.autorizador.util.CreditCardConverter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class resposible to define REST API methods.
 */
@RestController
@RequestMapping("api/v1")
public class CreditCardController {

    private transient CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService){
        this.creditCardService = creditCardService;
    }

    @PostMapping("/cartoes")
    public ResponseEntity<CreditCardRecord> createCreditCard(@RequestBody @Valid CreditCardRecord creditCardRecord){
        this.creditCardService.createCreditCard(CreditCardConverter.convertToDomain(creditCardRecord));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/cartoes/{numeroCartao}")
    public ResponseEntity<CreditCardRecord> createCreditCard(@PathVariable String numeroCartao){
        CreditCardRecord creditCardRecord = CreditCardConverter.convertToDto(this.creditCardService.findByCreditCardNumber(numeroCartao));
        return new ResponseEntity<>(creditCardRecord, HttpStatus.OK);
    }
}

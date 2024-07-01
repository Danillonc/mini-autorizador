package com.mini.autorizador.infrastructure.configuration;

import com.mini.autorizador.AutorizadorApplication;
import com.mini.autorizador.domain.repository.CardTransactionRepository;
import com.mini.autorizador.domain.repository.CreditCardRepository;
import com.mini.autorizador.domain.service.CardTransactionService;
import com.mini.autorizador.domain.service.CreditCardService;
import com.mini.autorizador.domain.service.impl.CardTransactionServiceImpl;
import com.mini.autorizador.domain.service.impl.CreditCardServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AutorizadorApplication.class)
public class BeanConfiguration {

    @Bean
    CreditCardService creditCardService(final CreditCardRepository creditCardRepository){
        return new CreditCardServiceImpl(creditCardRepository);
    }

    @Bean
    CardTransactionService cardTransactionService(final CreditCardService creditCardService, final CardTransactionRepository repository){
        return new CardTransactionServiceImpl(creditCardService, repository);
    }
}

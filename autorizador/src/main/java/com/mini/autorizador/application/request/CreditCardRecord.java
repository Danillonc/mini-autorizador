package com.mini.autorizador.application.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Class resposible to receive data from users requests.
 * @param numeroCartao - Numero do cartão de crédito.
 * @param senha - Senha do cartão de crédito.
 */
public record CreditCardRecord (
        @NotEmpty(message = "Numero do cartão não pode ser vazio.")
        @NotNull(message = "Numero do cartão deve ser preenchido.")
        String numeroCartao,
        @NotEmpty(message = "Senha do cartão não pode ser vazio.")
        @NotNull(message = "Senha do cartão deve ser preenchido.")
        String senha
) {}

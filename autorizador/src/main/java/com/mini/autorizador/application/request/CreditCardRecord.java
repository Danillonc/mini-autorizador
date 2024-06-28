package com.mini.autorizador.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Class resposible to receive data from users requests.
 * @param numeroCartao - Numero do cartão de crédito.
 * @param senha - Senha do cartão de crédito.
 */
public record CreditCardRecord (
        @NotBlank(message = "Numero do cartao não pode vazio.")
        @NotNull(message = "Numero do cartão deve ser preenchido.")
        String numeroCartao,
        @NotBlank(message = "Senha do cartao não pode vazio.")
        @NotNull(message = "Senha do cartão deve ser preenchido.")
        @Size(min = 4, message = "Senha fora do tamanho minimo 4.")
        String senha,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        double saldo
) {}

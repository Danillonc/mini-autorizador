package com.mini.autorizador.application.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CardTransactionRecord(
        @NotBlank(message = "Numero do cartao não pode vazio.")
        @NotNull(message = "Numero do cartão deve ser preenchido.")
        String numeroCartao,
        @NotBlank(message = "Senha do cartao não pode vazio.")
        @NotNull(message = "Senha do cartão deve ser preenchido.")
        @Size(min = 4, message = "Senha fora do tamanho minimo 4.")
        String senhaCartao,
        @Positive(message = "Valor deve ser positivo")
        @DecimalMin(value = "0.01", message = "Valor deve ser acima de 0.00")
        double valor
) {}

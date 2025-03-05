package com.example.demo.model.dto;

import com.example.demo.model.entity.Cliente;
import com.example.demo.model.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaPostRequestDTO (
        @NotNull
        Cliente titular,
        @Positive
        @NotNull
        Integer numero,
        @PositiveOrZero
        @NotNull
        Double limite) {

    public Conta convert() {
       return Conta.builder().titular(titular).numero(numero).limite(limite).build();
    }
}

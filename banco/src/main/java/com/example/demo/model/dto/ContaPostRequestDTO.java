package com.example.demo.model.dto;

import com.example.demo.model.entity.Cliente;
import com.example.demo.model.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaPostRequestDTO (
        @NotNull
        @Positive
        Integer idTitular,
        @Positive
        @NotNull
        Integer numero,
        @PositiveOrZero
        @NotNull
        Double limite) {

    public Conta convert(Cliente cliente) {
       return Conta.builder().titular(cliente).numero(numero).limite(limite).build();
    }
}

package com.example.demo.model.dto;

import com.example.demo.model.entity.Cliente;
import com.example.demo.model.entity.Conta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaPutRequestDTO(@NotNull
                                 Cliente titular,
                                 @PositiveOrZero
                                 @NotNull
                                 Double limite) {

    public Conta convert() {
        return Conta.builder().titular(titular).limite(limite).build();
    }
}

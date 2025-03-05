package com.example.demo.model.dto;

import com.example.demo.model.entity.Cliente;
import com.example.demo.model.entity.Conta;

import java.util.List;
import java.util.Set;

public record ClientePutRequestDTO(String nome,
                                   Long cpf,
                                   Set<Conta> contas) {
    public Cliente convert() {
        return Cliente.builder().nome(nome).cpf(cpf).contas(contas).build();
    }
}

package com.example.demo.model.dto;

import com.example.demo.model.entity.Cliente;
import com.example.demo.model.entity.Conta;

import java.util.List;

public record ClientePostRequestDTO2 (
        String nome,
        Long cpf,
        List<Conta> contas) {

    public Cliente convert() {
        return Cliente.builder().nome(this.nome).cpf(this.cpf).contas(this.contas).build();
    }
}

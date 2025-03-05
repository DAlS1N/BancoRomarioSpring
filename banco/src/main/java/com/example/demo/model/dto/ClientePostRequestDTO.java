package com.example.demo.model.dto;

import com.example.demo.model.entity.Cliente;

public record ClientePostRequestDTO (String nome,
                                     Long cpf) {

    public Cliente convert() {
        return Cliente.builder().nome(this.nome).cpf(this.cpf).build();
    }
}

package com.example.demo.model.dto;

import java.util.Set;

public record ClienteResponseDTO(
        Integer id,
        String nome,
        Long cpf,
        Set<ContaClienteResponseDTO> contas
) {
}

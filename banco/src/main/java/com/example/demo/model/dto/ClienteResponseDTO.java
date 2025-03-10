package com.example.demo.model.dto;

import java.util.List;

public record ClienteResponseDTO(
        Integer id,
        String nome,
        Long cpf,
        List<ContaClienteResponseDTO> contas
) {
}

package com.example.demo.model.dto;

public record ContaClienteResponseDTO(
        Integer id,
        Double saldo,
        Double limite,
        Integer numero
) {
}

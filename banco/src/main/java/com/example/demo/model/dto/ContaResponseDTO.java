package com.example.demo.model.dto;


public record ContaResponseDTO(
        Integer id,
        Integer numero,
        Double saldo,
        Double limite,
        ClienteContaGetResponseDTO titular) {



}

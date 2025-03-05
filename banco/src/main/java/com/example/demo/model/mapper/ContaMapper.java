//package com.example.demo.model.mapper;
//
//import com.example.demo.model.dto.ContaClienteResponseDTO;
//import com.example.demo.model.dto.ContaGetResponseDTO;
//import com.example.demo.model.dto.ContaPostRequestDTO;
//import com.example.demo.model.entity.Conta;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface ContaMapper {
//
//    ContaMapper INSTANCE =
//            Mappers.getMapper(
//                    ContaMapper.class);
//
//    @Mapping(conditionExpression = "java(conta.getId())")
//    ContaGetResponseDTO
//    contaToContaGetResponseDTO(
//            Conta conta);
//    ContaClienteResponseDTO
//    contaToContaClienteReponseDTO(
//            Conta conta
//    );
//    ContaPostRequestDTO
//    contaToContaPostRequestDTO(
//            Conta conta
//    );
//}

package com.example.demo.controller;

import com.example.demo.model.dto.ContaPutRequestDTO;
import com.example.demo.model.dto.ContaResponseDTO;
import com.example.demo.model.dto.ContaPostRequestDTO;
import com.example.demo.model.entity.Conta;
import com.example.demo.service.ContaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/conta")
@AllArgsConstructor
public class ContaController {

    private ContaService service;

    @GetMapping("/buscarContas")
    public List<ContaResponseDTO> buscarTodasAsContas(){
        List<Conta> contasList = service.buscarContas();
        return contasList.stream().map(Conta::convertToContaResponseDTO).toList();
    }

    @GetMapping("/buscarContasPaginado")
    public Page<ContaResponseDTO> buscarTodasAsContasPaginado(@PageableDefault(size = 20,
            sort = "saldo", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Conta> contaPage =
                service.buscarContas(pageable);
        List<ContaResponseDTO> contaResponseDTOList =
                contaPage.getContent().stream().map(
                        Conta::convertToContaResponseDTO).toList();
        return new PageImpl<>(
                contaResponseDTOList,
                contaPage.getPageable(),
                contaPage.getTotalElements()
        );
    }

    @GetMapping("/buscarUmaConta/{id}")
    public ContaResponseDTO buscarContaPorId(@PathVariable Integer id){
         Conta conta = service.buscarConta(id);
         return conta.convertToContaResponseDTO();
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponseDTO cadastrar(@RequestBody @Valid ContaPostRequestDTO contaDto){
        Conta conta = service.criarConta(contaDto);
        return conta.convertToContaResponseDTO();
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarConta(@PathVariable Integer id){
        service.removerConta(id);
    }

    @PutMapping("/atualizarConta/{id}")
    public ContaResponseDTO atualizarConta(
            @PathVariable Integer id,
            @RequestBody ContaPutRequestDTO contaDto){
        Conta conta = service.atualizarConta(
                id, contaDto);
        return conta.convertToContaResponseDTO();
    }

    @PatchMapping("/atualizarLimite/{id}")
    public ContaResponseDTO alterarLimite(
            @PathVariable Integer id,
            @RequestParam Double limite){
        Conta conta = service.alterarLimite(id,limite);
        return conta.convertToContaResponseDTO();
    }
}

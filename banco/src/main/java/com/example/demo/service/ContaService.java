package com.example.demo.service;

import com.example.demo.model.dto.ContaPostRequestDTO;
import com.example.demo.model.dto.ContaPutRequestDTO;
import com.example.demo.model.dto.ContaResponseDTO;
import com.example.demo.model.entity.Conta;
import com.example.demo.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ContaService {

    private final ContaRepository repository;

    public Conta criarConta(ContaPostRequestDTO contaDTO) {
        Conta conta = contaDTO.convert();
        return repository.save(conta);
    }

    public List<Conta> buscarContas(){
        return repository.findAll();
    }

    public Page<Conta> buscarContas(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Conta buscarConta(Integer id) {
        return repository.findById(id).get();
    }

    public void removerConta(Integer id) {
        repository.deleteById(id);
    }

    public Conta atualizarConta(Integer id, ContaPutRequestDTO contaDto) {
        Conta conta = contaDto.convert();
        conta.setId(id);
        return repository.save(conta);
    }

    public Conta alterarLimite(Integer id, Double limite) {
        Conta conta = buscarConta(id);
        conta.setLimite(limite);
        return repository.save(conta);
    }
}

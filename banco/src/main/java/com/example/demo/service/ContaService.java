package com.example.demo.service;

import com.example.demo.model.dto.ContaPostRequestDTO;
import com.example.demo.model.dto.ContaPutRequestDTO;
import com.example.demo.model.entity.Cliente;
import com.example.demo.model.entity.Conta;
import com.example.demo.repository.ContaRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository repository;
    @Lazy @Autowired
    private ClienteService clienteService;

    public Conta criarConta(ContaPostRequestDTO contaDTO) {
        Cliente cliente = clienteService.buscarCliente(contaDTO.idTitular());
        Conta conta = contaDTO.convert(cliente);
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
        Conta contaAntiga = buscarConta(id);
        Conta contaEditada = contaDto.convert();
        contaEditada.setId(id);
        contaEditada.setNumero(
                contaAntiga.getNumero());
        contaEditada.setSaldo(
                contaAntiga.getSaldo());
        return repository.save(contaEditada);
    }

    public Conta alterarLimite(Integer id, Double limite) {
        Conta conta = buscarConta(id);
        conta.setLimite(limite);
        return repository.save(conta);
    }
}

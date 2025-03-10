package com.example.demo.service;


import com.example.demo.model.dto.ClientePostRequestDTO2;
import com.example.demo.model.dto.ClientePutRequestDTO;
import com.example.demo.model.entity.Cliente;
import com.example.demo.model.entity.Conta;
import com.example.demo.repository.ClienteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ContaService contaService;
    private final ModelMapper modelMapper;


    public Cliente cadastrar(ClientePostRequestDTO2 clientePostRequestDTO) {
        Cliente cliente = clientePostRequestDTO.convert();
        return repository.save(cliente);
    }

    public Cliente editar(@Valid ClientePutRequestDTO clienteDTO,@NotNull @Positive Integer id) {
        if(repository.existsById(id)){
            Cliente clienteAtual = buscarCliente(id);

            Cliente clienteEditado =
                    clienteDTO.convert();
            if(clienteEditado.getContas() == null){
                clienteEditado.setContas(
                        clienteAtual.getContas());
            }
            clienteEditado.setId(id);
            return repository.save(clienteEditado);
        }
        throw new NoSuchElementException();
    }

    public Cliente alterarConta(@NotNull @Positive Integer id,
                                @NotNull @Positive Integer idConta) {
        Cliente cliente = repository.findById(id).get();
        Conta conta = contaService.buscarConta(idConta);
        if(cliente.getContas().contains(conta)){
            cliente.removerConta(conta);
        }else if(conta.getTitular() == null) {
                cliente.addConta(conta);
        }else {
            throw new RuntimeException();
        }
        return repository.save(cliente);
    }

    public Cliente buscarCliente(@NotNull @Positive Integer id) {
        Cliente cliente = repository.findById(id).get();
        System.out.println(cliente);
        return cliente;
    }

    public Page<Cliente> buscarClientes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void remover(@NotNull @Positive Integer id) {
        repository.deleteById(id);
    }
}

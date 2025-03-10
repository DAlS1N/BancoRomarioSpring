package com.example.demo.controller;

import com.example.demo.model.dto.ClientePostRequestDTO;
import com.example.demo.model.dto.ClientePostRequestDTO2;
import com.example.demo.model.dto.ClientePutRequestDTO;
import com.example.demo.model.dto.ClienteResponseDTO;
import com.example.demo.model.entity.Cliente;
import com.example.demo.service.ClienteService;
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
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping("/createCliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO cadastrar(@Valid @RequestBody ClientePostRequestDTO2 clientePostRequestDTO){
        Cliente cliente = service.cadastrar(clientePostRequestDTO);
        return cliente.convertToClienteResponseDTO();
    }

    @PutMapping("/updateCliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO editar(@Valid @RequestBody ClientePutRequestDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = service.editar(clienteDTO, id);
        return cliente.convertToClienteResponseDTO();
    }

    @PatchMapping("/updateItem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO alterarContas(@PathVariable Integer id,@RequestParam Integer idConta){
       Cliente cliente = service.alterarConta(id,idConta);
       return cliente.convertToClienteResponseDTO();
    }

    @GetMapping("/getCliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO buscarCliente(@PathVariable Integer id){
        Cliente cliente = service.buscarCliente(id);
        System.out.println(cliente);
        return cliente.convertToClienteResponseDTO();

    }


    @GetMapping("/getAllClientes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Page<ClienteResponseDTO> buscarClientes(@PageableDefault(
                                            size = 20,
                                            sort = "nome",
                                            direction = Sort.Direction.ASC)
                                            Pageable pageable){
        Page<Cliente> clientePage = service.buscarClientes(pageable);
        List<ClienteResponseDTO> content = clientePage.getContent().stream().map(
              Cliente::convertToClienteResponseDTO).toList();
        return new PageImpl<>(content,
                            clientePage.getPageable(),
                            clientePage.getTotalElements());
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCliente(@PathVariable Integer id){
        service.remover(id);
    }
}

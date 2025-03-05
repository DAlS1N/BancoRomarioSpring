package com.example.demo.model.entity;

import com.example.demo.model.dto.ClienteContaGetResponseDTO;
import com.example.demo.model.dto.ClienteResponseDTO;
import com.example.demo.model.dto.ContaClienteResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_cliente")
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Long cpf;
    @OneToMany(mappedBy = "titular")
    private Set<Conta> contas;

    public Set<Conta> getContas() {
        if(this.contas != null){
            return Collections.unmodifiableSet(contas);
        }
        return new HashSet<>();
    }

    public void addConta(@NotNull Conta conta){
        this.contas.add(conta);
    }

    public void removerConta(@NotNull Conta conta){
        this.contas.remove(conta);
    }

    public ClienteContaGetResponseDTO convertToClienteContaResponseDTO(){
        return new ClienteContaGetResponseDTO(
                this.id, this.nome, this.cpf
        );
    }

    public ClienteResponseDTO convertToClienteResponseDTO() {
        Set<ContaClienteResponseDTO> contasDto =
                this.contas.stream().map(
                        Conta::convertToContaClienteResponseDTO).
                        collect(Collectors.toSet());
        return new ClienteResponseDTO(this.id, this.nome, this.cpf, contasDto);
    }

    //    @ManyToMany
//    @JoinTable(
//            name = "tb_cliente_conta",
//            joinColumns =
//                @JoinColumn(name = "cliente_id"),
//            inverseJoinColumns =
//                @JoinColumn(name = "conta_id"))
//    @OneToOne(mappedBy = "titular")
//    private Conta conta;
}

package com.example.demo.model.entity;

import com.example.demo.model.dto.ClienteContaGetResponseDTO;
import com.example.demo.model.dto.ClienteResponseDTO;
import com.example.demo.model.dto.ContaClienteResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_cliente")
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Long cpf;
    @OneToMany(mappedBy = "titular", cascade = CascadeType.MERGE)
    private List<Conta> contas;

    public List<Conta> getContas() {
        if(this.contas == null){
            return new ArrayList<>();
        }
        return contas;
    }

    public void addConta(@NotNull Conta conta){
        if(this.contas.contains(conta)){
            throw new RuntimeException();
        }
        this.contas.add(conta);
        conta.setTitular(this);
    }

    public void removerConta(@NotNull Conta conta){
        if(!this.contas.contains(conta)){
            throw new RuntimeException();
        }
        this.contas.remove(conta);
        conta.setTitular(null);
    }

    public ClienteContaGetResponseDTO convertToClienteContaResponseDTO(){
        return new ClienteContaGetResponseDTO(
                this.id, this.nome, this.cpf
        );
    }

    public ClienteResponseDTO convertToClienteResponseDTO() {
        List<ContaClienteResponseDTO> contasDto =
                getContas().stream().map(
                        Conta::convertToContaClienteResponseDTO).
                        toList();
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

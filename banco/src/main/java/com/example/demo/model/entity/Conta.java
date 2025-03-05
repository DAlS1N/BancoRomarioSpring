package com.example.demo.model.entity;

import com.example.demo.model.dto.ClienteContaGetResponseDTO;
import com.example.demo.model.dto.ContaClienteResponseDTO;
import com.example.demo.model.dto.ContaResponseDTO;
import jakarta.persistence.*;
import lombok.*;


// -------- USO DO LOMBOK --------


@Data // Adiciona(GETTER, SETTER, CONSTRUTOR
     // (Tem que adicionar NonNull para os atributos que quer no Construtor (Se não Adicionar o construtor fica vazio)),
    // toString e EqualsAndHashCode) automaticamente.
@Entity
@Table(name = "tb_conta")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private Integer numero;
    @Builder.Default
    private Double saldo = 0.0;
    private Double limite;
//    @ManyToOne
//    @ManyToMany(mappedBy = "contas")
//    private List<Cliente> titulares;
    @ManyToOne
    @JoinColumn(name = "titular_id")
    private Cliente titular;


    public ContaClienteResponseDTO convertToContaClienteResponseDTO() {
        return new ContaClienteResponseDTO(
                this.id,
                this.saldo,
                this.limite,
                this.numero);
    }

    public ContaResponseDTO convertToContaResponseDTO() {
        ClienteContaGetResponseDTO titular =
                this.titular.convertToClienteContaResponseDTO();
        return new ContaResponseDTO(
                this.id,
                this.numero,
                this.saldo,
                this.limite,
                titular);
    }
}

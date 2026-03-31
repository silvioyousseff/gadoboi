package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "regiao")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RegiaoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_regiao", nullable = false)
    private String nomeRegiao;
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}

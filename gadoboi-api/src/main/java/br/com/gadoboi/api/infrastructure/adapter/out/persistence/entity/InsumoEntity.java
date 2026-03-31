package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "insumo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InsumoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_insumo", nullable = false)
    private String nomeInsumo;
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}

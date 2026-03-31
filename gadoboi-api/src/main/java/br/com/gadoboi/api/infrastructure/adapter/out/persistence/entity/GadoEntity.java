package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gado")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GadoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_gado", nullable = false)
    private String nomeGado;
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}

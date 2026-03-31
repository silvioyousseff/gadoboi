package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "manejo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ManejoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_manejo", nullable = false)
    private String nomeManejo;
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}

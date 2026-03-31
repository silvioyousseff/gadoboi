package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "metodo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MetodoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_metodo", nullable = false)
    private String nomeMetodo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "racao_id", nullable = false)
    private RacaoEntity racao;
}

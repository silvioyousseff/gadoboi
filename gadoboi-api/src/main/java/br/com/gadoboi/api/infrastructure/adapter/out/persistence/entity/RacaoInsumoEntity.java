package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "racao_insumo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RacaoInsumoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "insumo_id", nullable = false)
    private Long insumoId;
    @Column(name = "qtd_insumo", nullable = false, precision = 10, scale = 4)
    private BigDecimal qtdInsumo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "racao_id", nullable = false)
    private RacaoEntity racao;
}

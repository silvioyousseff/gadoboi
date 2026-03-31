package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rendimento")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RendimentoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cliente_id")
    private Long clienteId;
    @Column(name = "qtd_animais", nullable = false)
    private Integer qtdAnimais;
    @Column(name = "periodo_de_tratamento", nullable = false)
    private Integer periodoDeTratamento;
    @Column(name = "tamanho_da_pastagem", nullable = false, precision = 12, scale = 2)
    private BigDecimal tamanhoDaPastagem;
    @Column(name = "peso_inicial", nullable = false, precision = 10, scale = 2)
    private BigDecimal pesoInicial;
    @Column(name = "ganho_de_peso_esperado", nullable = false, precision = 10, scale = 4)
    private BigDecimal ganhoDePesoEsperado;
    @Column(name = "rendimento_carcaca", nullable = false, precision = 5, scale = 4)
    private BigDecimal rendimentoCarcaca;
    @Column(name = "preco_arroba", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoArroba;
    @Column(name = "preco_por_quilo_con", nullable = false, precision = 10, scale = 4)
    private BigDecimal precoPorQuiloCon;
    @Column(name = "taxa_lotacao", precision = 10, scale = 4)
    private BigDecimal taxaLotacao;
    @Column(name = "qtd_concentrado_diariamente", precision = 10, scale = 4)
    private BigDecimal qtdConcentradoDiariamente;
    @Column(name = "qtd_concentrado_total", precision = 12, scale = 4)
    private BigDecimal qtdConcentradoTotal;
    @Column(name = "preco_concentrado_dia_animal", precision = 10, scale = 4)
    private BigDecimal precoConcentradoDiaAnimal;
    @Column(name = "ganho_por_cabeca_dia", precision = 10, scale = 4)
    private BigDecimal ganhoPorCabecaDia;
    @Column(name = "custo_total", precision = 14, scale = 2)
    private BigDecimal custoTotal;
    @Column(name = "ganho_total", precision = 14, scale = 2)
    private BigDecimal ganhoTotal;
    @Column(name = "resultado", precision = 14, scale = 2)
    private BigDecimal resultado;
    @Column(name = "calculado_em")
    private LocalDateTime calculadoEm;
}

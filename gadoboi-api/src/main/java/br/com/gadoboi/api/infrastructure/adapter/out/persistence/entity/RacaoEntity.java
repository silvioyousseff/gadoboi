package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "racao")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RacaoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_racao", nullable = false)
    private String nomeRacao;
    @Column(name = "taxa_rendimento", precision = 10, scale = 4)
    private BigDecimal taxaRendimento;
    private LocalDate data;
    private LocalTime horario;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "qtd_gado")
    private Integer qtdGado;
    @Column(name = "qtd_tratamento_dia")
    private Integer qtdTratamentoDia;
    @Column(name = "peso_inicial", precision = 10, scale = 2)
    private BigDecimal pesoInicial;
    @Column(name = "gado_id")
    private Long gadoId;
    @Column(name = "regiao_id")
    private Long regiaoId;
    @Column(name = "manejo_id")
    private Long manejoId;
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
    @OneToMany(mappedBy = "racao", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RacaoInsumoEntity> insumos = new ArrayList<>();
    @OneToMany(mappedBy = "racao", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MetodoEntity> metodos = new ArrayList<>();
}

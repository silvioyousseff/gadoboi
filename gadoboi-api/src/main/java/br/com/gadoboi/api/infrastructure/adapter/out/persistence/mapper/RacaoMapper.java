package br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper;

import br.com.gadoboi.api.domain.model.Racao;
import br.com.gadoboi.api.domain.model.RacaoInsumo;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.MetodoEntity;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.RacaoEntity;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.RacaoInsumoEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class RacaoMapper {

    public Racao toDomain(RacaoEntity e) {
        List<RacaoInsumo> insumos = e.getInsumos().stream()
                .map(ri -> new RacaoInsumo(ri.getId(), ri.getInsumoId(), null,
                        ri.getQtdInsumo() != null ? ri.getQtdInsumo().doubleValue() : null))
                .toList();
        List<String> metodos = e.getMetodos().stream()
                .map(MetodoEntity::getNomeMetodo)
                .toList();
        return new Racao(
                e.getId(), e.getNomeRacao(),
                e.getTaxaRendimento() != null ? e.getTaxaRendimento().doubleValue() : null,
                e.getData(), e.getHorario(), e.getStatus(),
                e.getQtdGado(), e.getQtdTratamentoDia(),
                e.getPesoInicial() != null ? e.getPesoInicial().doubleValue() : null,
                e.getGadoId(), e.getRegiaoId(), e.getManejoId(), e.getClienteId(),
                insumos, metodos
        );
    }

    public RacaoEntity toEntity(Racao d) {
        RacaoEntity entity = RacaoEntity.builder()
                .id(d.id())
                .nomeRacao(d.nomeRacao())
                .taxaRendimento(d.taxaRendimento() != null ? BigDecimal.valueOf(d.taxaRendimento()) : null)
                .data(d.data())
                .horario(d.horario())
                .status(d.status())
                .qtdGado(d.qtdGado())
                .qtdTratamentoDia(d.qtdTratamentoDia())
                .pesoInicial(d.pesoInicial() != null ? BigDecimal.valueOf(d.pesoInicial()) : null)
                .gadoId(d.gadoId())
                .regiaoId(d.regiaoId())
                .manejoId(d.manejoId())
                .clienteId(d.clienteId())
                .build();

        if (d.insumos() != null) {
            d.insumos().forEach(ri -> {
                RacaoInsumoEntity riEntity = RacaoInsumoEntity.builder()
                        .id(ri.id())
                        .insumoId(ri.insumoId())
                        .qtdInsumo(ri.qtdInsumo() != null ? BigDecimal.valueOf(ri.qtdInsumo()) : BigDecimal.ZERO)
                        .racao(entity)
                        .build();
                entity.getInsumos().add(riEntity);
            });
        }
        if (d.metodos() != null) {
            d.metodos().forEach(m -> {
                MetodoEntity metodoEntity = MetodoEntity.builder()
                        .nomeMetodo(m)
                        .racao(entity)
                        .build();
                entity.getMetodos().add(metodoEntity);
            });
        }
        return entity;
    }
}

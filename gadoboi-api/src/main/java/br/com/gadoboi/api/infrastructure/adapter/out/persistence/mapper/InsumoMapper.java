package br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper;

import br.com.gadoboi.api.domain.model.Insumo;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.InsumoEntity;
import org.springframework.stereotype.Component;

@Component
public class InsumoMapper {
    public Insumo toDomain(InsumoEntity e) {
        return new Insumo(e.getId(), e.getNomeInsumo(), e.getClienteId());
    }
    public InsumoEntity toEntity(Insumo d) {
        return InsumoEntity.builder()
                .id(d.id())
                .nomeInsumo(d.nomeInsumo())
                .clienteId(d.clienteId())
                .build();
    }
}

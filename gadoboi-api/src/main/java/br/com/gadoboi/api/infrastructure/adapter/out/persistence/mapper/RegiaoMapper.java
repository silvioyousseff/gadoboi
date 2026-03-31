package br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper;

import br.com.gadoboi.api.domain.model.Regiao;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.RegiaoEntity;
import org.springframework.stereotype.Component;

@Component
public class RegiaoMapper {
    public Regiao toDomain(RegiaoEntity e) {
        return new Regiao(e.getId(), e.getNomeRegiao(), e.getClienteId());
    }
    public RegiaoEntity toEntity(Regiao d) {
        return RegiaoEntity.builder()
                .id(d.id())
                .nomeRegiao(d.nomeRegiao())
                .clienteId(d.clienteId())
                .build();
    }
}

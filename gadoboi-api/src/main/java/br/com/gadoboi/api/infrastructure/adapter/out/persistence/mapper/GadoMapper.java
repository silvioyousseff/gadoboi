package br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper;

import br.com.gadoboi.api.domain.model.Gado;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.GadoEntity;
import org.springframework.stereotype.Component;

@Component
public class GadoMapper {
    public Gado toDomain(GadoEntity e) {
        return new Gado(e.getId(), e.getNomeGado(), e.getClienteId());
    }
    public GadoEntity toEntity(Gado d) {
        return GadoEntity.builder()
                .id(d.id())
                .nomeGado(d.nomeGado())
                .clienteId(d.clienteId())
                .build();
    }
}

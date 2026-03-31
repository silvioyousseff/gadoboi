package br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper;

import br.com.gadoboi.api.domain.model.Manejo;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.ManejoEntity;
import org.springframework.stereotype.Component;

@Component
public class ManejoMapper {
    public Manejo toDomain(ManejoEntity e) {
        return new Manejo(e.getId(), e.getNomeManejo(), e.getClienteId());
    }
    public ManejoEntity toEntity(Manejo d) {
        return ManejoEntity.builder()
                .id(d.id())
                .nomeManejo(d.nomeManejo())
                .clienteId(d.clienteId())
                .build();
    }
}

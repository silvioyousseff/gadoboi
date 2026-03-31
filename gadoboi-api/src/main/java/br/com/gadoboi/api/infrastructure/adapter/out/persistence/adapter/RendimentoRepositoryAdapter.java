package br.com.gadoboi.api.infrastructure.adapter.out.persistence.adapter;

import br.com.gadoboi.api.domain.model.Rendimento;
import br.com.gadoboi.api.domain.port.out.RendimentoRepository;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper.RendimentoMapper;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository.RendimentoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RendimentoRepositoryAdapter implements RendimentoRepository {

    private final RendimentoJpaRepository jpa;
    private final RendimentoMapper mapper;

    @Override
    public Rendimento salvar(Rendimento rendimento, Long clienteId) {
        jpa.save(mapper.toEntity(rendimento, clienteId));
        return rendimento;
    }
}

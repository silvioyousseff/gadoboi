package br.com.gadoboi.api.infrastructure.adapter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.gadoboi.api.domain.model.Racao;
import br.com.gadoboi.api.domain.port.out.RacaoRepository;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.RacaoEntity;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper.RacaoMapper;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository.RacaoJpaRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RacaoRepositoryAdapter implements RacaoRepository {

    private final RacaoJpaRepository jpa;
    private final RacaoMapper mapper;

    @Override
    @Transactional
    public Racao salvar(Racao racao) {
        RacaoEntity entity = mapper.toEntity(racao);
        return mapper.toDomain(jpa.save(entity));
    }

    @Override
    public Optional<Racao> buscarPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Racao> buscarPorCliente(Long clienteId) {
        return jpa.findByClienteId(clienteId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Racao> buscarParaVenda() {
        return jpa.findByStatus(true).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deletar(Long id) {
        jpa.deleteById(id);
    }
}

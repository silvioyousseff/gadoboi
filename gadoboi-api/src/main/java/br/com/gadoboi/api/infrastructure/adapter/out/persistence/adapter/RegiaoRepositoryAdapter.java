package br.com.gadoboi.api.infrastructure.adapter.out.persistence.adapter;

import br.com.gadoboi.api.domain.model.Regiao;
import br.com.gadoboi.api.domain.port.out.RegiaoRepository;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper.RegiaoMapper;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository.RegiaoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegiaoRepositoryAdapter implements RegiaoRepository {

    private final RegiaoJpaRepository jpa;
    private final RegiaoMapper mapper;

    @Override
    public Regiao salvar(Regiao regiao) {
        return mapper.toDomain(jpa.save(mapper.toEntity(regiao)));
    }

    @Override
    public Optional<Regiao> buscarPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Regiao> buscarPorCliente(Long clienteId) {
        return jpa.findByClienteId(clienteId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deletar(Long id) {
        jpa.deleteById(id);
    }
}

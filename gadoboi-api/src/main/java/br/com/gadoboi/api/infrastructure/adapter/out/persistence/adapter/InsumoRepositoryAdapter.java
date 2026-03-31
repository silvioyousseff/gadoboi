package br.com.gadoboi.api.infrastructure.adapter.out.persistence.adapter;

import br.com.gadoboi.api.domain.model.Insumo;
import br.com.gadoboi.api.domain.port.out.InsumoRepository;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper.InsumoMapper;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository.InsumoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InsumoRepositoryAdapter implements InsumoRepository {

    private final InsumoJpaRepository jpa;
    private final InsumoMapper mapper;

    @Override
    public Insumo salvar(Insumo insumo) {
        return mapper.toDomain(jpa.save(mapper.toEntity(insumo)));
    }

    @Override
    public Optional<Insumo> buscarPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Insumo> buscarPorCliente(Long clienteId) {
        return jpa.findByClienteId(clienteId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deletar(Long id) {
        jpa.deleteById(id);
    }
}

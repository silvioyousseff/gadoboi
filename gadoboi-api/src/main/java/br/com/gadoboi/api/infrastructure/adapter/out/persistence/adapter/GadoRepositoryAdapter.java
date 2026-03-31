package br.com.gadoboi.api.infrastructure.adapter.out.persistence.adapter;

import br.com.gadoboi.api.domain.model.Gado;
import br.com.gadoboi.api.domain.port.out.GadoRepository;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper.GadoMapper;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository.GadoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GadoRepositoryAdapter implements GadoRepository {

    private final GadoJpaRepository jpa;
    private final GadoMapper mapper;

    @Override
    public Gado salvar(Gado gado) {
        return mapper.toDomain(jpa.save(mapper.toEntity(gado)));
    }

    @Override
    public Optional<Gado> buscarPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Gado> buscarPorCliente(Long clienteId) {
        return jpa.findByClienteId(clienteId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deletar(Long id) {
        jpa.deleteById(id);
    }
}

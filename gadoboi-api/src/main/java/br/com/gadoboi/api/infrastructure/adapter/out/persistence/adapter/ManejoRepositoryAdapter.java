package br.com.gadoboi.api.infrastructure.adapter.out.persistence.adapter;

import br.com.gadoboi.api.domain.model.Manejo;
import br.com.gadoboi.api.domain.port.out.ManejoRepository;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper.ManejoMapper;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository.ManejoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ManejoRepositoryAdapter implements ManejoRepository {

    private final ManejoJpaRepository jpa;
    private final ManejoMapper mapper;

    @Override
    public Manejo salvar(Manejo manejo) {
        return mapper.toDomain(jpa.save(mapper.toEntity(manejo)));
    }

    @Override
    public Optional<Manejo> buscarPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Manejo> buscarPorCliente(Long clienteId) {
        return jpa.findByClienteId(clienteId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deletar(Long id) {
        jpa.deleteById(id);
    }
}

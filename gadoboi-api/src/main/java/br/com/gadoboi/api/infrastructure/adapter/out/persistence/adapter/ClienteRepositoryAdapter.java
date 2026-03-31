package br.com.gadoboi.api.infrastructure.adapter.out.persistence.adapter;

import br.com.gadoboi.api.domain.model.Cliente;
import br.com.gadoboi.api.domain.port.out.ClienteRepository;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper.ClienteMapper;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final ClienteJpaRepository jpa;
    private final ClienteMapper mapper;

    @Override
    public Cliente salvar(Cliente cliente, String senhaHash) {
        return mapper.toDomain(jpa.save(mapper.toEntity(cliente, senhaHash)));
    }

    @Override
    public Optional<Cliente> buscarPorEmail(String email) {
        return jpa.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existePorEmail(String email) {
        return jpa.existsByEmail(email);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return jpa.existsByCpf(cpf);
    }

    @Override
    public String buscarSenhaHashPorEmail(String email) {
        return jpa.findSenhaHashByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + email));
    }
}

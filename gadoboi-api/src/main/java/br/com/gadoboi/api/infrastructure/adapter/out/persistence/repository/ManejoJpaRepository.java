package br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository;

import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.ManejoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ManejoJpaRepository extends JpaRepository<ManejoEntity, Long> {
    List<ManejoEntity> findByClienteId(Long clienteId);
    Optional<ManejoEntity> findByIdAndClienteId(Long id, Long clienteId);
}

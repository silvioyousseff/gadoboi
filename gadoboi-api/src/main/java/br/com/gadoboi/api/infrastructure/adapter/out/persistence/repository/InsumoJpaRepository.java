package br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository;

import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.InsumoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InsumoJpaRepository extends JpaRepository<InsumoEntity, Long> {
    List<InsumoEntity> findByClienteId(Long clienteId);
    Optional<InsumoEntity> findByIdAndClienteId(Long id, Long clienteId);
}

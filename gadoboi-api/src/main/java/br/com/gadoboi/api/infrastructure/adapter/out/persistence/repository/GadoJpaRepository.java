package br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository;

import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.GadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GadoJpaRepository extends JpaRepository<GadoEntity, Long> {
    List<GadoEntity> findByClienteId(Long clienteId);
    Optional<GadoEntity> findByIdAndClienteId(Long id, Long clienteId);
}

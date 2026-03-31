package br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository;

import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.RegiaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RegiaoJpaRepository extends JpaRepository<RegiaoEntity, Long> {
    List<RegiaoEntity> findByClienteId(Long clienteId);
    Optional<RegiaoEntity> findByIdAndClienteId(Long id, Long clienteId);
}

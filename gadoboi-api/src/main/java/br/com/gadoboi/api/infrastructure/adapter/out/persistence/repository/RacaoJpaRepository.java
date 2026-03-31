package br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository;

import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.RacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RacaoJpaRepository extends JpaRepository<RacaoEntity, Long> {
    List<RacaoEntity> findByClienteId(Long clienteId);
    Optional<RacaoEntity> findByIdAndClienteId(Long id, Long clienteId);
    List<RacaoEntity> findByClienteIdAndStatus(Long clienteId, Boolean status);
    List<RacaoEntity> findByStatus(Boolean status);
}

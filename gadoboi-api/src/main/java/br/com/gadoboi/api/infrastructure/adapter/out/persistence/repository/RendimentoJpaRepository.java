package br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository;

import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.RendimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendimentoJpaRepository extends JpaRepository<RendimentoEntity, Long> {
}

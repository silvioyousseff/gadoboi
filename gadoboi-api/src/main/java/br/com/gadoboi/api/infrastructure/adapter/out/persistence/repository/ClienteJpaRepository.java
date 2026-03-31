package br.com.gadoboi.api.infrastructure.adapter.out.persistence.repository;

import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    @Query("SELECT c.senhaHash FROM ClienteEntity c WHERE c.email = :email")
    Optional<String> findSenhaHashByEmail(@Param("email") String email);
}

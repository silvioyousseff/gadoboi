package br.com.gadoboi.api.domain.port.out;

import br.com.gadoboi.api.domain.model.Cliente;
import java.util.Optional;

public interface ClienteRepository {
    Cliente salvar(Cliente cliente, String senhaHash);
    Optional<Cliente> buscarPorEmail(String email);
    Optional<Cliente> buscarPorId(Long id);
    boolean existePorEmail(String email);
    boolean existePorCpf(String cpf);
    String buscarSenhaHashPorEmail(String email);
}

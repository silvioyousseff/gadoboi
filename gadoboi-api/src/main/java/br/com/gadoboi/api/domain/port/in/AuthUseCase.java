package br.com.gadoboi.api.domain.port.in;

import br.com.gadoboi.api.domain.model.Cliente;

public interface AuthUseCase {
    String login(String email, String senha);
    Cliente cadastrar(Cliente cliente, String senhaPlain);
}

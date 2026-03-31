package br.com.gadoboi.api.application.service;

import br.com.gadoboi.api.domain.model.Cliente;
import br.com.gadoboi.api.domain.port.in.AuthUseCase;
import br.com.gadoboi.api.domain.port.out.ClienteRepository;
import br.com.gadoboi.api.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String login(String email, String senha) {
        String senhaHash = clienteRepository.buscarSenhaHashPorEmail(email);
        if (!passwordEncoder.matches(senha, senhaHash)) {
            throw new RuntimeException("Credenciais inválidas");
        }
        Cliente cliente = clienteRepository.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return jwtService.gerar(cliente.id(), cliente.email());
    }

    @Override
    public Cliente cadastrar(Cliente cliente, String senhaPlain) {
        if (clienteRepository.existePorEmail(cliente.email())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (clienteRepository.existePorCpf(cliente.cpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        String senhaHash = passwordEncoder.encode(senhaPlain);
        return clienteRepository.salvar(cliente, senhaHash);
    }
}

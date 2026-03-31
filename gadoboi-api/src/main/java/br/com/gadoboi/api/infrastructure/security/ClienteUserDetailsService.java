package br.com.gadoboi.api.infrastructure.security;

import br.com.gadoboi.api.domain.model.Cliente;
import br.com.gadoboi.api.domain.port.out.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteUserDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado: " + email));
        String senhaHash = clienteRepository.buscarSenhaHashPorEmail(email);
        return new ClienteDetails(cliente.id(), cliente.email(), senhaHash);
    }
}

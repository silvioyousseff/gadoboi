package br.com.gadoboi.api.application.service;

import br.com.gadoboi.api.domain.model.Gado;
import br.com.gadoboi.api.domain.port.in.GadoUseCase;
import br.com.gadoboi.api.domain.port.out.GadoRepository;
import br.com.gadoboi.api.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GadoService implements GadoUseCase {

    private final GadoRepository gadoRepository;

    @Override
    public Gado criar(String nomeGado, Long clienteId) {
        return gadoRepository.salvar(new Gado(null, nomeGado, clienteId));
    }

    @Override
    public Gado atualizar(Long id, String nomeGado, Long clienteId) {
        buscarPorId(id, clienteId);
        return gadoRepository.salvar(new Gado(id, nomeGado, clienteId));
    }

    @Override
    public void remover(Long id, Long clienteId) {
        buscarPorId(id, clienteId);
        gadoRepository.deletar(id);
    }

    @Override
    public List<Gado> listarPorCliente(Long clienteId) {
        return gadoRepository.buscarPorCliente(clienteId);
    }

    @Override
    public Gado buscarPorId(Long id, Long clienteId) {
        Gado gado = gadoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gado não encontrado"));
        if (!gado.clienteId().equals(clienteId)) throw new ResourceNotFoundException("Gado não encontrado");
        return gado;
    }
}

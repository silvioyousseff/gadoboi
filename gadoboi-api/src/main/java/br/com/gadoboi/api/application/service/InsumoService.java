package br.com.gadoboi.api.application.service;

import br.com.gadoboi.api.domain.model.Insumo;
import br.com.gadoboi.api.domain.port.in.InsumoUseCase;
import br.com.gadoboi.api.domain.port.out.InsumoRepository;
import br.com.gadoboi.api.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsumoService implements InsumoUseCase {

    private final InsumoRepository insumoRepository;

    @Override
    public Insumo criar(String nomeInsumo, Long clienteId) {
        return insumoRepository.salvar(new Insumo(null, nomeInsumo, clienteId));
    }

    @Override
    public Insumo atualizar(Long id, String nomeInsumo, Long clienteId) {
        buscarPorId(id, clienteId);
        return insumoRepository.salvar(new Insumo(id, nomeInsumo, clienteId));
    }

    @Override
    public void remover(Long id, Long clienteId) {
        buscarPorId(id, clienteId);
        insumoRepository.deletar(id);
    }

    @Override
    public List<Insumo> listarPorCliente(Long clienteId) {
        return insumoRepository.buscarPorCliente(clienteId);
    }

    @Override
    public Insumo buscarPorId(Long id, Long clienteId) {
        Insumo insumo = insumoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo não encontrado"));
        if (!insumo.clienteId().equals(clienteId)) throw new ResourceNotFoundException("Insumo não encontrado");
        return insumo;
    }
}

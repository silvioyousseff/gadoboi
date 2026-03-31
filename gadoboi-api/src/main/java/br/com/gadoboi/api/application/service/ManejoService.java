package br.com.gadoboi.api.application.service;

import br.com.gadoboi.api.domain.model.Manejo;
import br.com.gadoboi.api.domain.port.in.ManejoUseCase;
import br.com.gadoboi.api.domain.port.out.ManejoRepository;
import br.com.gadoboi.api.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManejoService implements ManejoUseCase {

    private final ManejoRepository manejoRepository;

    @Override
    public Manejo criar(String nomeManejo, Long clienteId) {
        return manejoRepository.salvar(new Manejo(null, nomeManejo, clienteId));
    }

    @Override
    public Manejo atualizar(Long id, String nomeManejo, Long clienteId) {
        buscarPorId(id, clienteId);
        return manejoRepository.salvar(new Manejo(id, nomeManejo, clienteId));
    }

    @Override
    public void remover(Long id, Long clienteId) {
        buscarPorId(id, clienteId);
        manejoRepository.deletar(id);
    }

    @Override
    public List<Manejo> listarPorCliente(Long clienteId) {
        return manejoRepository.buscarPorCliente(clienteId);
    }

    @Override
    public Manejo buscarPorId(Long id, Long clienteId) {
        Manejo manejo = manejoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manejo não encontrado"));
        if (!manejo.clienteId().equals(clienteId)) throw new ResourceNotFoundException("Manejo não encontrado");
        return manejo;
    }
}

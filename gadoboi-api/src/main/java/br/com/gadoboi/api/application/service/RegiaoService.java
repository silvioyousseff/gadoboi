package br.com.gadoboi.api.application.service;

import br.com.gadoboi.api.domain.model.Regiao;
import br.com.gadoboi.api.domain.port.in.RegiaoUseCase;
import br.com.gadoboi.api.domain.port.out.RegiaoRepository;
import br.com.gadoboi.api.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegiaoService implements RegiaoUseCase {

    private final RegiaoRepository regiaoRepository;

    @Override
    public Regiao criar(String nomeRegiao, Long clienteId) {
        return regiaoRepository.salvar(new Regiao(null, nomeRegiao, clienteId));
    }

    @Override
    public Regiao atualizar(Long id, String nomeRegiao, Long clienteId) {
        buscarPorId(id, clienteId);
        return regiaoRepository.salvar(new Regiao(id, nomeRegiao, clienteId));
    }

    @Override
    public void remover(Long id, Long clienteId) {
        buscarPorId(id, clienteId);
        regiaoRepository.deletar(id);
    }

    @Override
    public List<Regiao> listarPorCliente(Long clienteId) {
        return regiaoRepository.buscarPorCliente(clienteId);
    }

    @Override
    public Regiao buscarPorId(Long id, Long clienteId) {
        Regiao regiao = regiaoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada"));
        if (!regiao.clienteId().equals(clienteId)) throw new ResourceNotFoundException("Região não encontrada");
        return regiao;
    }
}

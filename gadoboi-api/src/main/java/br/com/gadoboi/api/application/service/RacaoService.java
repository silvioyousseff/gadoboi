package br.com.gadoboi.api.application.service;

import br.com.gadoboi.api.domain.model.Racao;
import br.com.gadoboi.api.domain.port.in.RacaoUseCase;
import br.com.gadoboi.api.domain.port.out.RacaoRepository;
import br.com.gadoboi.api.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RacaoService implements RacaoUseCase {

    private final RacaoRepository racaoRepository;

    @Override
    @Transactional
    public Racao criar(Racao racao, Long clienteId) {
        Racao withCliente = new Racao(null, racao.nomeRacao(), racao.taxaRendimento(),
                racao.data(), racao.horario(), racao.status(), racao.qtdGado(),
                racao.qtdTratamentoDia(), racao.pesoInicial(), racao.gadoId(),
                racao.regiaoId(), racao.manejoId(), clienteId, racao.insumos(), racao.metodos());
        return racaoRepository.salvar(withCliente);
    }

    @Override
    @Transactional
    public Racao atualizar(Long id, Racao racao, Long clienteId) {
        buscarPorId(id, clienteId);
        Racao updated = new Racao(id, racao.nomeRacao(), racao.taxaRendimento(),
                racao.data(), racao.horario(), racao.status(), racao.qtdGado(),
                racao.qtdTratamentoDia(), racao.pesoInicial(), racao.gadoId(),
                racao.regiaoId(), racao.manejoId(), clienteId, racao.insumos(), racao.metodos());
        return racaoRepository.salvar(updated);
    }

    @Override
    @Transactional
    public void remover(Long id, Long clienteId) {
        buscarPorId(id, clienteId);
        racaoRepository.deletar(id);
    }

    @Override
    public List<Racao> listarPorCliente(Long clienteId) {
        return racaoRepository.buscarPorCliente(clienteId);
    }

    @Override
    public List<Racao> listarParaVenda(Long clienteId) {
        return racaoRepository.buscarParaVenda();
    }

    @Override
    public Racao buscarPorId(Long id, Long clienteId) {
        Racao racao = racaoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ração não encontrada"));
        if (!racao.clienteId().equals(clienteId)) throw new ResourceNotFoundException("Ração não encontrada");
        return racao;
    }
}

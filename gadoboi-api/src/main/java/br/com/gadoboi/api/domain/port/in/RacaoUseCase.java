package br.com.gadoboi.api.domain.port.in;

import br.com.gadoboi.api.domain.model.Racao;
import java.util.List;

public interface RacaoUseCase {
    Racao criar(Racao racao, Long clienteId);
    Racao atualizar(Long id, Racao racao, Long clienteId);
    void remover(Long id, Long clienteId);
    List<Racao> listarPorCliente(Long clienteId);
    List<Racao> listarParaVenda(Long clienteId);
    Racao buscarPorId(Long id, Long clienteId);
}

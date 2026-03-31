package br.com.gadoboi.api.domain.port.in;

import br.com.gadoboi.api.domain.model.Regiao;
import java.util.List;

public interface RegiaoUseCase {
    Regiao criar(String nomeRegiao, Long clienteId);
    Regiao atualizar(Long id, String nomeRegiao, Long clienteId);
    void remover(Long id, Long clienteId);
    List<Regiao> listarPorCliente(Long clienteId);
    Regiao buscarPorId(Long id, Long clienteId);
}

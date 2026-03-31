package br.com.gadoboi.api.domain.port.in;

import br.com.gadoboi.api.domain.model.Gado;
import java.util.List;

public interface GadoUseCase {
    Gado criar(String nomeGado, Long clienteId);
    Gado atualizar(Long id, String nomeGado, Long clienteId);
    void remover(Long id, Long clienteId);
    List<Gado> listarPorCliente(Long clienteId);
    Gado buscarPorId(Long id, Long clienteId);
}

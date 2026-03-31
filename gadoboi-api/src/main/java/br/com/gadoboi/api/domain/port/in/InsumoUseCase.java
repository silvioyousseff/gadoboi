package br.com.gadoboi.api.domain.port.in;

import br.com.gadoboi.api.domain.model.Insumo;
import java.util.List;

public interface InsumoUseCase {
    Insumo criar(String nomeInsumo, Long clienteId);
    Insumo atualizar(Long id, String nomeInsumo, Long clienteId);
    void remover(Long id, Long clienteId);
    List<Insumo> listarPorCliente(Long clienteId);
    Insumo buscarPorId(Long id, Long clienteId);
}

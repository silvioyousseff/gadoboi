package br.com.gadoboi.api.domain.port.in;

import br.com.gadoboi.api.domain.model.Manejo;
import java.util.List;

public interface ManejoUseCase {
    Manejo criar(String nomeManejo, Long clienteId);
    Manejo atualizar(Long id, String nomeManejo, Long clienteId);
    void remover(Long id, Long clienteId);
    List<Manejo> listarPorCliente(Long clienteId);
    Manejo buscarPorId(Long id, Long clienteId);
}

package br.com.gadoboi.api.domain.port.out;

import br.com.gadoboi.api.domain.model.Manejo;
import java.util.List;
import java.util.Optional;

public interface ManejoRepository {
    Manejo salvar(Manejo manejo);
    Optional<Manejo> buscarPorId(Long id);
    List<Manejo> buscarPorCliente(Long clienteId);
    void deletar(Long id);
}

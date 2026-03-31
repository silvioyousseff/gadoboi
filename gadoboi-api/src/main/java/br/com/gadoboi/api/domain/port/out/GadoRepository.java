package br.com.gadoboi.api.domain.port.out;

import br.com.gadoboi.api.domain.model.Gado;
import java.util.List;
import java.util.Optional;

public interface GadoRepository {
    Gado salvar(Gado gado);
    Optional<Gado> buscarPorId(Long id);
    List<Gado> buscarPorCliente(Long clienteId);
    void deletar(Long id);
}

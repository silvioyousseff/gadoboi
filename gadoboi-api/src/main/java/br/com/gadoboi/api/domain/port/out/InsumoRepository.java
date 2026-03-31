package br.com.gadoboi.api.domain.port.out;

import br.com.gadoboi.api.domain.model.Insumo;
import java.util.List;
import java.util.Optional;

public interface InsumoRepository {
    Insumo salvar(Insumo insumo);
    Optional<Insumo> buscarPorId(Long id);
    List<Insumo> buscarPorCliente(Long clienteId);
    void deletar(Long id);
}

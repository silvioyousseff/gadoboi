package br.com.gadoboi.api.domain.port.out;

import br.com.gadoboi.api.domain.model.Regiao;
import java.util.List;
import java.util.Optional;

public interface RegiaoRepository {
    Regiao salvar(Regiao regiao);
    Optional<Regiao> buscarPorId(Long id);
    List<Regiao> buscarPorCliente(Long clienteId);
    void deletar(Long id);
}

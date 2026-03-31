package br.com.gadoboi.api.domain.port.out;

import br.com.gadoboi.api.domain.model.Racao;
import java.util.List;
import java.util.Optional;

public interface RacaoRepository {
    Racao salvar(Racao racao);
    Optional<Racao> buscarPorId(Long id);
    List<Racao> buscarPorCliente(Long clienteId);
    List<Racao> buscarParaVenda();
    void deletar(Long id);
}

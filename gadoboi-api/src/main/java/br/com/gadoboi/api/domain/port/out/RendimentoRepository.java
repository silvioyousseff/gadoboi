package br.com.gadoboi.api.domain.port.out;

import br.com.gadoboi.api.domain.model.Rendimento;

public interface RendimentoRepository {
    Rendimento salvar(Rendimento rendimento, Long clienteId);
}

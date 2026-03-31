package br.com.gadoboi.api.domain.port.in;

import br.com.gadoboi.api.domain.model.Rendimento;

public interface RendimentoUseCase {
    Rendimento calcular(int qtdAnimais, int periodoDeTratamento, double tamanhoDaPastagem,
                        double pesoInicial, double ganhoDePesoEsperado, double rendimentoCarcaca,
                        double precoArroba, double precoPorQuiloCon, Long clienteId);
}

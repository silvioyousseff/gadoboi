package br.com.gadoboi.api.application.service;

import br.com.gadoboi.api.domain.model.Rendimento;
import br.com.gadoboi.api.domain.port.in.RendimentoUseCase;
import br.com.gadoboi.api.domain.port.out.RendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RendimentoService implements RendimentoUseCase {

    private final RendimentoRepository rendimentoRepository;

    @Override
    public Rendimento calcular(int qtdAnimais, int periodoDeTratamento, double tamanhoDaPastagem,
                               double pesoInicial, double ganhoDePesoEsperado, double rendimentoCarcaca,
                               double precoArroba, double precoPorQuiloCon, Long clienteId) {
        Rendimento result = Rendimento.calcular(qtdAnimais, periodoDeTratamento, tamanhoDaPastagem,
                pesoInicial, ganhoDePesoEsperado, rendimentoCarcaca, precoArroba, precoPorQuiloCon);
        return rendimentoRepository.salvar(result, clienteId);
    }
}

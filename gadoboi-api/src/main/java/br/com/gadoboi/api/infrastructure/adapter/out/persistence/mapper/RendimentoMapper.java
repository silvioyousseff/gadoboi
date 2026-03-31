package br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper;

import br.com.gadoboi.api.domain.model.Rendimento;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.RendimentoEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class RendimentoMapper {

    public RendimentoEntity toEntity(Rendimento d, Long clienteId) {
        return RendimentoEntity.builder()
                .clienteId(clienteId)
                .qtdAnimais(d.qtdAnimais())
                .periodoDeTratamento(d.periodoDeTratamento())
                .tamanhoDaPastagem(bd(d.tamanhoDaPastagem()))
                .pesoInicial(bd(d.pesoInicial()))
                .ganhoDePesoEsperado(bd(d.ganhoDePesoEsperado()))
                .rendimentoCarcaca(bd(d.rendimentoCarcaca()))
                .precoArroba(bd(d.precoArroba()))
                .precoPorQuiloCon(bd(d.precoPorQuiloCon()))
                .taxaLotacao(bd(d.taxaLotacao()))
                .qtdConcentradoDiariamente(bd(d.qtdConcentradoDiariamente()))
                .qtdConcentradoTotal(bd(d.qtdConcentradoTotal()))
                .precoConcentradoDiaAnimal(bd(d.precoConcentradoDiaAnimal()))
                .ganhoPorCabecaDia(bd(d.ganhoPorCabecaDia()))
                .custoTotal(bd(d.custoTotal()))
                .ganhoTotal(bd(d.ganhoTotal()))
                .resultado(bd(d.resultado()))
                .calculadoEm(LocalDateTime.now())
                .build();
    }

    private BigDecimal bd(Double v) {
        return v != null ? BigDecimal.valueOf(v) : null;
    }
}

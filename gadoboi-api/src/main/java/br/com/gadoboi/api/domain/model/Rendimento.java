package br.com.gadoboi.api.domain.model;

/**
 * Livestock profitability calculation.
 * Formula derived from legacy Rendimento.calcular():
 *   taxaLotacao            = qtdAnimais / tamanhoDaPastagem
 *   qtdConcentradoDiario   = pesoInicial * 0.01
 *   qtdConcentradoTotal    = qtdConcentradoDiario * qtdAnimais * periodoDeTratamento
 *   custoTotal             = precoPorQuiloCon * qtdConcentradoTotal
 *   ganhoTotal             = ganhoDePesoEsperado * qtdAnimais * periodoDeTratamento
 *                            * rendimentoCarcaca * precoArroba
 *   resultado              = ganhoTotal - custoTotal
 */
public record Rendimento(
        Long id,
        Integer qtdAnimais,
        Integer periodoDeTratamento,
        Double tamanhoDaPastagem,
        Double pesoInicial,
        Double ganhoDePesoEsperado,
        Double rendimentoCarcaca,
        Double precoArroba,
        Double precoPorQuiloCon,
        // computed results
        Double taxaLotacao,
        Double qtdConcentradoDiariamente,
        Double qtdConcentradoTotal,
        Double precoConcentradoDiaAnimal,
        Double ganhoPorCabecaDia,
        Double custoTotal,
        Double ganhoTotal,
        Double resultado
) {
    public static Rendimento calcular(
            int qtdAnimais, int periodoDeTratamento, double tamanhoDaPastagem,
            double pesoInicial, double ganhoDePesoEsperado, double rendimentoCarcaca,
            double precoArroba, double precoPorQuiloCon
    ) {
        double taxaLotacao = qtdAnimais / tamanhoDaPastagem;
        double qtdConcentradoDiario = pesoInicial * 0.01;
        double qtdConcentradoTotal = qtdConcentradoDiario * qtdAnimais * periodoDeTratamento;
        double custoTotal = precoPorQuiloCon * qtdConcentradoTotal;
        double precoConcentradoDiaAnimal = precoPorQuiloCon * qtdConcentradoDiario;
        double ganhoPorCabecaDia = ganhoDePesoEsperado * rendimentoCarcaca * precoArroba * (1.0 / 15);
        double ganhoTotal = ganhoDePesoEsperado * qtdAnimais * periodoDeTratamento * rendimentoCarcaca * precoArroba;
        double resultado = ganhoTotal - custoTotal;

        return new Rendimento(null, qtdAnimais, periodoDeTratamento, tamanhoDaPastagem,
                pesoInicial, ganhoDePesoEsperado, rendimentoCarcaca, precoArroba,
                precoPorQuiloCon, taxaLotacao, qtdConcentradoDiario, qtdConcentradoTotal,
                precoConcentradoDiaAnimal, ganhoPorCabecaDia, custoTotal, ganhoTotal, resultado);
    }
}

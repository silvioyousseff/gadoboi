package br.com.gadoboi.api.domain.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class RendimentoUnitTest {

    @Test
    void calcularDeveRetornarResultadoPositivoComDadosValidos() {
        Rendimento r = Rendimento.calcular(100, 90, 50.0, 350.0, 1.2, 0.52, 280.0, 0.60);

        assertThat(r.taxaLotacao()).isEqualTo(2.0, within(0.001));
        assertThat(r.qtdConcentradoDiariamente()).isEqualTo(3.5, within(0.001));
        assertThat(r.qtdConcentradoTotal()).isCloseTo(31500.0, within(1.0));
        assertThat(r.custoTotal()).isCloseTo(18900.0, within(1.0));
        assertThat(r.resultado()).isGreaterThan(0);
    }

    @Test
    void calcularDeveComputarTaxaLotacaoCorretamente() {
        Rendimento r = Rendimento.calcular(200, 60, 100.0, 300.0, 1.0, 0.50, 250.0, 0.55);
        assertThat(r.taxaLotacao()).isEqualTo(2.0, within(0.001));
    }

    @Test
    void formulasTodas_deveRetornar6Formulas() {
        assertThat(Formula.todas()).hasSize(6);
    }

    @Test
    void formulaIngredientesDevemSomarCemPorcento() {
        Formula.todas().forEach(f -> {
            double soma = f.ingredientes().stream().mapToDouble(Formula.FormulaIngrediente::percentual).sum();
            assertThat(soma).isCloseTo(100.0, within(0.01));
        });
    }
}

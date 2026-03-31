package br.com.gadoboi.api.domain.model;

import java.util.List;

public record Formula(
        String tipo,
        String descricao,
        List<FormulaIngrediente> ingredientes
) {
    public record FormulaIngrediente(String nome, double percentual) {}

    public static List<Formula> todas() {
        return List.of(
            new Formula("CONFINAMENTO_1", "Confinamento - Fórmula A", List.of(
                new FormulaIngrediente("Milho", 78.34),
                new FormulaIngrediente("Farelo de Soja", 16.0),
                new FormulaIngrediente("Ureia", 2.04),
                new FormulaIngrediente("Sulfato de Amônio", 0.36),
                new FormulaIngrediente("Mix Mineral", 1.20),
                new FormulaIngrediente("Ionóforo", 0.06),
                new FormulaIngrediente("Calcário Calcítico", 2.0)
            )),
            new Formula("CONFINAMENTO_2", "Confinamento - Fórmula B", List.of(
                new FormulaIngrediente("Milho", 75.46),
                new FormulaIngrediente("Farelo de Soja", 20.0),
                new FormulaIngrediente("Ureia", 1.87),
                new FormulaIngrediente("Sulfato de Amônio", 0.33),
                new FormulaIngrediente("Mix Mineral", 1.0),
                new FormulaIngrediente("Ionóforo", 0.04),
                new FormulaIngrediente("Calcário Calcítico", 1.30)
            )),
            new Formula("SEMI_CHUVA", "Semi-confinamento - Estação Chuvosa", List.of(
                new FormulaIngrediente("Milho", 73.95),
                new FormulaIngrediente("Farelo de Soja", 20.80),
                new FormulaIngrediente("Ureia", 1.70),
                new FormulaIngrediente("Sulfato de Amônio", 0.30),
                new FormulaIngrediente("Mix Mineral", 2.0),
                new FormulaIngrediente("Ionóforo", 0.05),
                new FormulaIngrediente("Calcário Calcítico", 1.20)
            )),
            new Formula("SEMI_ESTIAGEM", "Semi-confinamento - Estação de Estiagem", List.of(
                new FormulaIngrediente("Milho", 70.86),
                new FormulaIngrediente("Farelo de Soja", 24.9),
                new FormulaIngrediente("Ureia", 1.28),
                new FormulaIngrediente("Sulfato de Amônio", 0.22),
                new FormulaIngrediente("Mix Mineral", 1.50),
                new FormulaIngrediente("Ionóforo", 0.04),
                new FormulaIngrediente("Calcário Calcítico", 1.20)
            )),
            new Formula("PROTEINATO_CHUVA", "Proteinato - Estação Chuvosa", List.of(
                new FormulaIngrediente("Milho", 32.0),
                new FormulaIngrediente("Farelo de Soja", 25.0),
                new FormulaIngrediente("Ureia", 6.8),
                new FormulaIngrediente("Sulfato de Amônio", 1.2),
                new FormulaIngrediente("Mix Mineral", 15.0),
                new FormulaIngrediente("Sal Branco", 20.0)
            )),
            new Formula("PROTEINATO_ESTIAGEM", "Proteinato - Estação de Estiagem", List.of(
                new FormulaIngrediente("Milho", 22.0),
                new FormulaIngrediente("Farelo de Soja", 28.0),
                new FormulaIngrediente("Ureia", 12.8),
                new FormulaIngrediente("Sulfato de Amônio", 2.2),
                new FormulaIngrediente("Mix Mineral", 15.0),
                new FormulaIngrediente("Sal Branco", 20.0)
            ))
        );
    }
}

package br.com.gadoboi.api.infrastructure.adapter.in.rest;

import br.com.gadoboi.api.domain.model.Formula;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Fórmulas", description = "Fórmulas de ração pré-definidas (confinamento, semi-confinamento, proteinato)")
@SecurityRequirement(name = "bearer-jwt")
@RestController
@RequestMapping("/api/v1/formulas")
public class FormulaController {

    @Operation(summary = "Listar todas as fórmulas")
    @GetMapping
    public List<Formula> listar() {
        return Formula.todas();
    }

    @Operation(summary = "Buscar fórmula por tipo", description = "Tipos disponíveis: CONFINAMENTO_1, CONFINAMENTO_2, SEMI_CHUVA, SEMI_ESTIAGEM, PROTEINATO_CHUVA, PROTEINATO_ESTIAGEM")
    @GetMapping("/{tipo}")
    public Formula buscar(@PathVariable String tipo) {
        return Formula.todas().stream()
                .filter(f -> f.tipo().equalsIgnoreCase(tipo))
                .findFirst()
                .orElseThrow(() -> new br.com.gadoboi.api.infrastructure.exception.ResourceNotFoundException(
                        "Fórmula não encontrada: " + tipo));
    }
}

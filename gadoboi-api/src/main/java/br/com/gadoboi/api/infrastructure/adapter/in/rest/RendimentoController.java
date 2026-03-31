package br.com.gadoboi.api.infrastructure.adapter.in.rest;

import br.com.gadoboi.api.domain.model.Rendimento;
import br.com.gadoboi.api.domain.port.in.RendimentoUseCase;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.RendimentoRequest;
import br.com.gadoboi.api.infrastructure.security.ClienteDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rendimento", description = "Cálculo de rentabilidade pecuária")
@SecurityRequirement(name = "bearer-jwt")
@RestController
@RequestMapping("/api/v1/rendimento")
@RequiredArgsConstructor
public class RendimentoController {

    private final RendimentoUseCase rendimentoUseCase;

    @Operation(summary = "Calcular rendimento", description = "Executa o cálculo de rentabilidade e persiste o resultado.")
    @PostMapping("/calcular")
    public Rendimento calcular(@Valid @RequestBody RendimentoRequest req,
                                @AuthenticationPrincipal ClienteDetails auth) {
        return rendimentoUseCase.calcular(
                req.qtdAnimais(), req.periodoDeTratamento(), req.tamanhoDaPastagem(),
                req.pesoInicial(), req.ganhoDePesoEsperado(), req.rendimentoCarcaca(),
                req.precoArroba(), req.precoPorQuiloCon(), auth.id()
        );
    }
}

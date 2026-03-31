package br.com.gadoboi.api.infrastructure.adapter.in.rest;

import br.com.gadoboi.api.domain.model.Racao;
import br.com.gadoboi.api.domain.model.RacaoInsumo;
import br.com.gadoboi.api.domain.port.in.RacaoUseCase;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.RacaoRequest;
import br.com.gadoboi.api.infrastructure.security.ClienteDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Ração", description = "Gerenciamento de fórmulas de ração e vendas do cliente autenticado")
@SecurityRequirement(name = "bearer-jwt")
@RestController
@RequestMapping("/api/v1/racao")
@RequiredArgsConstructor
public class RacaoController {

    private final RacaoUseCase racaoUseCase;

    @Operation(summary = "Listar rações do cliente")
    @GetMapping
    public List<Racao> listar(@AuthenticationPrincipal ClienteDetails auth) {
        return racaoUseCase.listarPorCliente(auth.id());
    }

    @Operation(summary = "Listar rações para venda")
    @GetMapping("/venda")
    public List<Racao> listarVenda(@AuthenticationPrincipal ClienteDetails auth) {
        return racaoUseCase.listarParaVenda(auth.id());
    }

    @Operation(summary = "Buscar ração por ID")
    @GetMapping("/{id}")
    public Racao buscar(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        return racaoUseCase.buscarPorId(id, auth.id());
    }

    @Operation(summary = "Criar ração")
    @PostMapping
    public ResponseEntity<Racao> criar(@Valid @RequestBody RacaoRequest req,
                                        @AuthenticationPrincipal ClienteDetails auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(racaoUseCase.criar(toRacao(null, req), auth.id()));
    }

    @Operation(summary = "Atualizar ração")
    @PutMapping("/{id}")
    public Racao atualizar(@PathVariable Long id, @Valid @RequestBody RacaoRequest req,
                            @AuthenticationPrincipal ClienteDetails auth) {
        return racaoUseCase.atualizar(id, toRacao(id, req), auth.id());
    }

    @Operation(summary = "Remover ração")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        racaoUseCase.remover(id, auth.id());
        return ResponseEntity.noContent().build();
    }

    private Racao toRacao(Long id, RacaoRequest req) {
        List<RacaoInsumo> insumos = req.insumos() == null ? List.of() : req.insumos().stream()
                .map(i -> new RacaoInsumo(null, i.insumoId(), null, i.qtdInsumo()))
                .toList();
        return new Racao(id, req.nomeRacao(), req.taxaRendimento(), req.data(), req.horario(),
                req.status(), req.qtdGado(), req.qtdTratamentoDia(), req.pesoInicial(),
                req.gadoId(), req.regiaoId(), req.manejoId(), null, insumos, req.metodos());
    }
}

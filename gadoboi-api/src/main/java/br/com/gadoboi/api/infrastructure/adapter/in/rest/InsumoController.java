package br.com.gadoboi.api.infrastructure.adapter.in.rest;

import br.com.gadoboi.api.domain.port.in.InsumoUseCase;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.InsumoRequest;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.InsumoResponse;
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

@Tag(name = "Insumo", description = "Gerenciamento de ingredientes (insumos) do cliente autenticado")
@SecurityRequirement(name = "bearer-jwt")
@RestController
@RequestMapping("/api/v1/insumo")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoUseCase insumoUseCase;

    @Operation(summary = "Listar insumos")
    @GetMapping
    public List<InsumoResponse> listar(@AuthenticationPrincipal ClienteDetails auth) {
        return insumoUseCase.listarPorCliente(auth.id()).stream().map(InsumoResponse::from).toList();
    }

    @Operation(summary = "Buscar insumo por ID")
    @GetMapping("/{id}")
    public InsumoResponse buscar(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        return InsumoResponse.from(insumoUseCase.buscarPorId(id, auth.id()));
    }

    @Operation(summary = "Cadastrar insumo")
    @PostMapping
    public ResponseEntity<InsumoResponse> criar(@Valid @RequestBody InsumoRequest req,
                                                 @AuthenticationPrincipal ClienteDetails auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InsumoResponse.from(insumoUseCase.criar(req.nomeInsumo(), auth.id())));
    }

    @Operation(summary = "Atualizar insumo")
    @PutMapping("/{id}")
    public InsumoResponse atualizar(@PathVariable Long id, @Valid @RequestBody InsumoRequest req,
                                     @AuthenticationPrincipal ClienteDetails auth) {
        return InsumoResponse.from(insumoUseCase.atualizar(id, req.nomeInsumo(), auth.id()));
    }

    @Operation(summary = "Remover insumo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        insumoUseCase.remover(id, auth.id());
        return ResponseEntity.noContent().build();
    }
}

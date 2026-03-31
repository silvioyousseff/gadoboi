package br.com.gadoboi.api.infrastructure.adapter.in.rest;

import br.com.gadoboi.api.domain.port.in.RegiaoUseCase;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.RegiaoRequest;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.RegiaoResponse;
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

@Tag(name = "Região", description = "Gerenciamento de regiões geográficas do cliente autenticado")
@SecurityRequirement(name = "bearer-jwt")
@RestController
@RequestMapping("/api/v1/regiao")
@RequiredArgsConstructor
public class RegiaoController {

    private final RegiaoUseCase regiaoUseCase;

    @Operation(summary = "Listar regiões")
    @GetMapping
    public List<RegiaoResponse> listar(@AuthenticationPrincipal ClienteDetails auth) {
        return regiaoUseCase.listarPorCliente(auth.id()).stream().map(RegiaoResponse::from).toList();
    }

    @Operation(summary = "Buscar região por ID")
    @GetMapping("/{id}")
    public RegiaoResponse buscar(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        return RegiaoResponse.from(regiaoUseCase.buscarPorId(id, auth.id()));
    }

    @Operation(summary = "Cadastrar região")
    @PostMapping
    public ResponseEntity<RegiaoResponse> criar(@Valid @RequestBody RegiaoRequest req,
                                                 @AuthenticationPrincipal ClienteDetails auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RegiaoResponse.from(regiaoUseCase.criar(req.nomeRegiao(), auth.id())));
    }

    @Operation(summary = "Atualizar região")
    @PutMapping("/{id}")
    public RegiaoResponse atualizar(@PathVariable Long id, @Valid @RequestBody RegiaoRequest req,
                                     @AuthenticationPrincipal ClienteDetails auth) {
        return RegiaoResponse.from(regiaoUseCase.atualizar(id, req.nomeRegiao(), auth.id()));
    }

    @Operation(summary = "Remover região")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        regiaoUseCase.remover(id, auth.id());
        return ResponseEntity.noContent().build();
    }
}

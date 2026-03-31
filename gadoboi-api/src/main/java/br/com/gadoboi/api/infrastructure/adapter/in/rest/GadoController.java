package br.com.gadoboi.api.infrastructure.adapter.in.rest;

import br.com.gadoboi.api.domain.port.in.GadoUseCase;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.GadoRequest;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.GadoResponse;
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

@Tag(name = "Gado", description = "Gerenciamento de raças de gado do cliente autenticado")
@SecurityRequirement(name = "bearer-jwt")
@RestController
@RequestMapping("/api/v1/gado")
@RequiredArgsConstructor
public class GadoController {

    private final GadoUseCase gadoUseCase;

    @Operation(summary = "Listar raças de gado")
    @GetMapping
    public List<GadoResponse> listar(@AuthenticationPrincipal ClienteDetails auth) {
        return gadoUseCase.listarPorCliente(auth.id()).stream().map(GadoResponse::from).toList();
    }

    @Operation(summary = "Buscar raça por ID")
    @GetMapping("/{id}")
    public GadoResponse buscar(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        return GadoResponse.from(gadoUseCase.buscarPorId(id, auth.id()));
    }

    @Operation(summary = "Cadastrar raça")
    @PostMapping
    public ResponseEntity<GadoResponse> criar(@Valid @RequestBody GadoRequest req,
                                               @AuthenticationPrincipal ClienteDetails auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GadoResponse.from(gadoUseCase.criar(req.nomeGado(), auth.id())));
    }

    @Operation(summary = "Atualizar raça")
    @PutMapping("/{id}")
    public GadoResponse atualizar(@PathVariable Long id, @Valid @RequestBody GadoRequest req,
                                   @AuthenticationPrincipal ClienteDetails auth) {
        return GadoResponse.from(gadoUseCase.atualizar(id, req.nomeGado(), auth.id()));
    }

    @Operation(summary = "Remover raça")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        gadoUseCase.remover(id, auth.id());
        return ResponseEntity.noContent().build();
    }
}

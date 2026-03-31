package br.com.gadoboi.api.infrastructure.adapter.in.rest;

import br.com.gadoboi.api.domain.port.in.ManejoUseCase;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.ManejoRequest;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.ManejoResponse;
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

@Tag(name = "Manejo", description = "Gerenciamento de tipos de manejo do cliente autenticado")
@SecurityRequirement(name = "bearer-jwt")
@RestController
@RequestMapping("/api/v1/manejo")
@RequiredArgsConstructor
public class ManejoController {

    private final ManejoUseCase manejoUseCase;

    @Operation(summary = "Listar manejos")
    @GetMapping
    public List<ManejoResponse> listar(@AuthenticationPrincipal ClienteDetails auth) {
        return manejoUseCase.listarPorCliente(auth.id()).stream().map(ManejoResponse::from).toList();
    }

    @Operation(summary = "Buscar manejo por ID")
    @GetMapping("/{id}")
    public ManejoResponse buscar(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        return ManejoResponse.from(manejoUseCase.buscarPorId(id, auth.id()));
    }

    @Operation(summary = "Cadastrar manejo")
    @PostMapping
    public ResponseEntity<ManejoResponse> criar(@Valid @RequestBody ManejoRequest req,
                                                 @AuthenticationPrincipal ClienteDetails auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ManejoResponse.from(manejoUseCase.criar(req.nomeManejo(), auth.id())));
    }

    @Operation(summary = "Atualizar manejo")
    @PutMapping("/{id}")
    public ManejoResponse atualizar(@PathVariable Long id, @Valid @RequestBody ManejoRequest req,
                                     @AuthenticationPrincipal ClienteDetails auth) {
        return ManejoResponse.from(manejoUseCase.atualizar(id, req.nomeManejo(), auth.id()));
    }

    @Operation(summary = "Remover manejo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id, @AuthenticationPrincipal ClienteDetails auth) {
        manejoUseCase.remover(id, auth.id());
        return ResponseEntity.noContent().build();
    }
}

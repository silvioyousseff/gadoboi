package br.com.gadoboi.api.infrastructure.adapter.in.rest;

import br.com.gadoboi.api.domain.model.Cliente;
import br.com.gadoboi.api.domain.model.Endereco;
import br.com.gadoboi.api.domain.port.in.AuthUseCase;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.AuthResponse;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.CadastroRequest;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticação", description = "Cadastro e login de clientes")
@SecurityRequirements
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @Operation(summary = "Login", description = "Autentica o cliente e retorna um JWT Bearer token.")
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest req) {
        String token = authUseCase.login(req.email(), req.senha());
        return new AuthResponse(token);
    }

    @Operation(summary = "Cadastro", description = "Registra um novo cliente. CPF e e-mail devem ser únicos.")
    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CadastroRequest req) {
        Endereco endereco = null;
        if (req.endereco() != null) {
            CadastroRequest.EnderecoDto e = req.endereco();
            endereco = new Endereco(null, e.bairro(), e.numero(), e.complemento(), e.cidade(), e.estado());
        }
        Cliente cliente = new Cliente(null, req.nome(), req.sobrenome(), req.email(), req.cpf(),
                req.rg(), req.telefone(), req.celular(), req.sexo(), req.dataNasc(), endereco);
        authUseCase.cadastrar(cliente, req.senha());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CadastroRequest(
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank @Email String email,
        @NotBlank String cpf,
        String rg,
        String telefone,
        String celular,
        String sexo,
        LocalDate dataNasc,
        @NotBlank @Size(min = 6) String senha,
        EnderecoDto endereco
) {
    public record EnderecoDto(
            String bairro,
            String numero,
            String complemento,
            @NotBlank String cidade,
            @NotBlank String estado
    ) {}
}

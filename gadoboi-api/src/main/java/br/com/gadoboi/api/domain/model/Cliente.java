package br.com.gadoboi.api.domain.model;

import java.time.LocalDate;

public record Cliente(
        Long id,
        String nome,
        String sobrenome,
        String email,
        String cpf,
        String rg,
        String telefone,
        String celular,
        String sexo,
        LocalDate dataNasc,
        Endereco endereco
) {}

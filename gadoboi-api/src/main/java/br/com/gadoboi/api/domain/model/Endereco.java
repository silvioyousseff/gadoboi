package br.com.gadoboi.api.domain.model;

public record Endereco(
        Long id,
        String bairro,
        String numero,
        String complemento,
        String cidade,
        String estado
) {}

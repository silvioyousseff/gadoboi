package br.com.gadoboi.api.domain.model;

public record Regiao(
        Long id,
        String nomeRegiao,
        Long clienteId
) {}

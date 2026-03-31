package br.com.gadoboi.api.domain.model;

public record Gado(
        Long id,
        String nomeGado,
        Long clienteId
) {}

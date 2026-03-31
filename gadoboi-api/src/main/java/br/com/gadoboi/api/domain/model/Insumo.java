package br.com.gadoboi.api.domain.model;

public record Insumo(
        Long id,
        String nomeInsumo,
        Long clienteId
) {}

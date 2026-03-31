package br.com.gadoboi.api.domain.model;

public record Manejo(
        Long id,
        String nomeManejo,
        Long clienteId
) {}

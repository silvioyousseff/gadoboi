package br.com.gadoboi.api.domain.model;

public record RacaoInsumo(
        Long id,
        Long insumoId,
        String nomeInsumo,
        Double qtdInsumo
) {}

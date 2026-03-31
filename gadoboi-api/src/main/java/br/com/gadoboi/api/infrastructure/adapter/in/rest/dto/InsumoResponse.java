package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

import br.com.gadoboi.api.domain.model.Insumo;

public record InsumoResponse(Long id, String nomeInsumo) {
    public static InsumoResponse from(Insumo insumo) {
        return new InsumoResponse(insumo.id(), insumo.nomeInsumo());
    }
}

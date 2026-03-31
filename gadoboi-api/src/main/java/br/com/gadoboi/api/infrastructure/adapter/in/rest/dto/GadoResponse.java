package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

import br.com.gadoboi.api.domain.model.Gado;

public record GadoResponse(Long id, String nomeGado) {
    public static GadoResponse from(Gado gado) {
        return new GadoResponse(gado.id(), gado.nomeGado());
    }
}

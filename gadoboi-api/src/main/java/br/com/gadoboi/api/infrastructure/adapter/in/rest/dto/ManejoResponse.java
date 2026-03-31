package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

import br.com.gadoboi.api.domain.model.Manejo;

public record ManejoResponse(Long id, String nomeManejo) {
    public static ManejoResponse from(Manejo manejo) {
        return new ManejoResponse(manejo.id(), manejo.nomeManejo());
    }
}

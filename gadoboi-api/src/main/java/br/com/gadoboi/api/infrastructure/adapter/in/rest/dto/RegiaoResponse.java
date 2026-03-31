package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

import br.com.gadoboi.api.domain.model.Regiao;

public record RegiaoResponse(Long id, String nomeRegiao) {
    public static RegiaoResponse from(Regiao regiao) {
        return new RegiaoResponse(regiao.id(), regiao.nomeRegiao());
    }
}

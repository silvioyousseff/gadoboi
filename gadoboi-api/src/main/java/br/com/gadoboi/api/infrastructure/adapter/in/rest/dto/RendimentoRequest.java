package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RendimentoRequest(
        @NotNull @Min(1) Integer qtdAnimais,
        @NotNull @Min(1) Integer periodoDeTratamento,
        @NotNull @Positive Double tamanhoDaPastagem,
        @NotNull @Positive Double pesoInicial,
        @NotNull @Positive Double ganhoDePesoEsperado,
        @NotNull @Positive Double rendimentoCarcaca,
        @NotNull @Positive Double precoArroba,
        @NotNull @Positive Double precoPorQuiloCon
) {}

package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record GadoRequest(@NotBlank String nomeGado) {}

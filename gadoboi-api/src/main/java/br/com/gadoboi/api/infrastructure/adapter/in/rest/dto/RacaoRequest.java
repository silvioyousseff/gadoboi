package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RacaoRequest(
        @NotBlank String nomeRacao,
        Double taxaRendimento,
        LocalDate data,
        LocalTime horario,
        String status,
        Integer qtdGado,
        Integer qtdTratamentoDia,
        Double pesoInicial,
        Long gadoId,
        Long regiaoId,
        Long manejoId,
        List<InsumoItem> insumos,
        List<String> metodos
) {
    public record InsumoItem(Long insumoId, Double qtdInsumo) {}
}

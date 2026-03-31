package br.com.gadoboi.api.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record Racao(
        Long id,
        String nomeRacao,
        Double taxaRendimento,
        LocalDate data,
        LocalTime horario,
        Boolean status,
        Integer qtdGado,
        Integer qtdTratamentoDia,
        Double pesoInicial,
        Long gadoId,
        Long regiaoId,
        Long manejoId,
        Long clienteId,
        List<RacaoInsumo> insumos,
        List<String> metodos
) {}

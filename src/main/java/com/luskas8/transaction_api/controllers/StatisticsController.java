package com.luskas8.transaction_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luskas8.transaction_api.controllers.dtos.StatisticsDTO;
import com.luskas8.transaction_api.services.StatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    @Operation(description = "Busca estadísticas das transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<StatisticsDTO> getStatistics(
        @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer lastSecondsInterval
    ) {
        return ResponseEntity.ok(statisticsService.calculateTransactionsStatistics(lastSecondsInterval));
    }
}

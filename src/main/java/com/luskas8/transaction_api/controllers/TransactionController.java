package com.luskas8.transaction_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luskas8.transaction_api.controllers.dtos.TransactionDTO;
import com.luskas8.transaction_api.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @Operation(description = "Adiciona transação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação adicionada"),
        @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionDTO transaction) {
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Deleta transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transações deletadas"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> clearTransactions() {
        transactionService.clearTransactions();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}

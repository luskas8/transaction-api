package com.luskas8.transaction_api.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luskas8.transaction_api.controllers.dtos.TransactionDTO;
import com.luskas8.transaction_api.exceptions.UnprocessableEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

    private final List<TransactionDTO> transactions = new ArrayList<>();

    public void addTransaction(TransactionDTO transaction) {
        log.info("Adding transaction: {}", transaction);
        if (transaction.dateTime().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora da transação não podem ser futuras");
            throw new UnprocessableEntity("Data e hora da transação não podem ser futuras");
        }
        if (transaction.value() < 0) {
            log.error("Valor da transação não pode ser menor ou igual a zero");
            throw new UnprocessableEntity("Valor da transação não pode ser menor ou igual a zero");
        }

        transactions.add(transaction);
    }

    public void clearTransactions() {
        log.info("Clearing transactions");
        transactions.clear();
    }

    public List<TransactionDTO> getTransactionsFromLastSeconds(Integer lastSecondsInterval) {
        log.info("Getting transactions from the last {} seconds", lastSecondsInterval);
        OffsetDateTime intervalDateTime = OffsetDateTime.now().minusSeconds(lastSecondsInterval);
    
        return transactions.stream().filter(transaction -> transaction.dateTime().isAfter(intervalDateTime)).toList();
    }
}

package com.luskas8.transaction_api.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luskas8.transaction_api.controllers.dtos.StatisticsDTO;
import com.luskas8.transaction_api.controllers.dtos.TransactionDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    public final TransactionService transactionService;

    public StatisticsDTO calculateTransactionsStatistics(Integer lastSecondsInterval) {
        List<TransactionDTO> transactions = transactionService.getTransactionsFromLastSeconds(lastSecondsInterval);

        if (transactions.isEmpty()) {
            return new StatisticsDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics statistics = transactions.stream()
            .mapToDouble(transaction -> transaction.value()).summaryStatistics();

        return new StatisticsDTO(
            statistics.getCount(),
            statistics.getSum(),
            statistics.getAverage(),
            statistics.getMax(),
            statistics.getMin()
        );
    }
}

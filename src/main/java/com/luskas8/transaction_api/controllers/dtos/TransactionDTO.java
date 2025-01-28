package com.luskas8.transaction_api.controllers.dtos;

import java.time.OffsetDateTime;

public record TransactionDTO(double value, OffsetDateTime dateTime) {
}

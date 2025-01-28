package com.luskas8.transaction_api.controllers.dtos;

public record StatisticsDTO(
    Long count,
    Double sum,
    Double avg,
    Double max,
    Double min
) {
}

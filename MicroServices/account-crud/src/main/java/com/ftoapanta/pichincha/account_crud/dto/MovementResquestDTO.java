package com.ftoapanta.pichincha.account_crud.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MovementResquestDTO {
    private Long id;
    private Long accountId;
    private LocalDate date;
    private String transactionType;
    private Double value;
    private Double balance;
}

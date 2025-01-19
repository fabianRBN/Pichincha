package com.ftoapanta.pichincha.client_crud.dto;

import com.ftoapanta.pichincha.client_crud.entities.Account;
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

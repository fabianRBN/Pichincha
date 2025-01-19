package com.ftoapanta.pichincha.client_crud.dto;

import com.ftoapanta.pichincha.client_crud.entities.Client;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequestDTO {
    private Long id;
    private Long clientId;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
}

package com.ftoapanta.pichincha.account_crud.dto;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.*;
@Data
@Builder
public class AccountRequestDTO {
    private Long id;

    @NotNull(message = "El ID del cliente no puede ser nulo.")
    private Long clientId;

    @NotBlank(message = "El número de cuenta no puede estar vacío.")
    @Size(min = 10, max = 20, message = "El número de cuenta debe tener entre 10 y 20 caracteres.")
    @Pattern(regexp = "^[0-9]+$", message = "El número de cuenta solo puede contener dígitos.")
    private String accountNumber;

    @NotBlank(message = "El tipo de cuenta es obligatorio.")
    @Pattern(regexp = "^(AHORROS|CORRIENTE)$", message = "El tipo de cuenta debe ser 'AHORROS' o 'CORRIENTE'.")
    private String accountType;

    @NotNull(message = "El saldo inicial no puede ser nulo.")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo.")
    private Double initialBalance;

    @NotNull(message = "El estado de la cuenta es obligatorio.")
    private Boolean status;
}

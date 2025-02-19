package com.ftoapanta.pichincha.account_crud.dto;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class MovementResquestDTO {
    private Long id;

    @NotNull(message = "El ID de la cuenta no puede ser nulo.")
    private Long accountId;

    @NotNull(message = "La fecha no puede ser nula.")
    @PastOrPresent(message = "La fecha del movimiento no puede ser futura.")
    private LocalDate date;

    @NotBlank(message = "El tipo de transacción es obligatorio.")
    @Pattern(regexp = "^(DEPOSITO|RETIRO)$", message = "El tipo de transacción debe ser 'DEPOSITO' o 'RETIRO'.")
    private String transactionType;

    @NotNull(message = "El valor del movimiento no puede ser nulo.")
    @DecimalMin(value = "0.01", message = "El valor del movimiento debe ser mayor a 0.")
    private Double value;

    @NotNull(message = "El saldo no puede ser nulo.")
    @DecimalMin(value = "0.0", message = "El saldo no puede ser negativo.")
    private Double balance;

}

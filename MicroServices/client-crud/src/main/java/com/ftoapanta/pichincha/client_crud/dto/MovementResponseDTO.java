package com.ftoapanta.pichincha.client_crud.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovementResponseDTO {
    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
    private Double available;
}
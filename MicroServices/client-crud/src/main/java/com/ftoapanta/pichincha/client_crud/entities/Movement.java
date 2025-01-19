package com.ftoapanta.pichincha.client_crud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movimientos")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE ,CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "cuenta_id")
    @JsonIgnore
    private Account account;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    @Column(name = "fecha", nullable = false)
    private LocalDate date;

    @Column(name = "tipo_movimiento")
    private String transactionType;

    @Column(name = "valor")
    private Double value;

    @Column(name = "saldo")
    private Double balance;

    @Column(name = "disponible")
    private Double available;
}

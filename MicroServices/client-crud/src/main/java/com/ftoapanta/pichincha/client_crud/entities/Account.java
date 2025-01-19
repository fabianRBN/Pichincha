package com.ftoapanta.pichincha.client_crud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cuenta")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE ,CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Client client;

    @Column(name = "numero_cuenta")
    private String accountNumber;

    @Column(name = "tipo_cuenta")
    private String accountType;

    @Column(name = "saldo_inicial")
    private Double initialBalance;

    @Column(name = "estado")
    private Boolean status;
}

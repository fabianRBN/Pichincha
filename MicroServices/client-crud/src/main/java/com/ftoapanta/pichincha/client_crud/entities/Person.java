package com.ftoapanta.pichincha.client_crud.entities;
import com.ftoapanta.pichincha.client_crud.utils.Gender;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "genero")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "edad")
    private int age;

    @Column(name = "identificacion")
    private String identification;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phoneNumber;
}

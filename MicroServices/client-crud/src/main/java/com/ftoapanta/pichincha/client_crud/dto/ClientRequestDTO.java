package com.ftoapanta.pichincha.client_crud.dto;

import com.ftoapanta.pichincha.client_crud.utils.Gender;
import lombok.Data;

@Data
public class ClientRequestDTO {
    private Long id;
    private String name;
    private String address;
    private Gender gender;
    private int age;
    private String phoneNumber;
    private boolean status;
    private String password;
    private String identification;
}

package com.ftoapanta.pichincha.account_crud.dto;

import com.ftoapanta.pichincha.account_crud.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String address;
    private Gender gender;
    private int age;
    private String phoneNumber;
    private boolean status;
    private String identification;

}

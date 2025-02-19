package com.ftoapanta.pichincha.account_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponseDTO {

    private Object data;
    private boolean success;
    private String message;
    private String error;
    private Long totalElements;

}

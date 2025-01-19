package com.ftoapanta.pichincha.client_crud.dto;

import lombok.Data;

@Data
public class BaseResponseDTO {

    private Object data;
    private boolean success;
    private String message;
    private String error;
    private Long totalElements;

}

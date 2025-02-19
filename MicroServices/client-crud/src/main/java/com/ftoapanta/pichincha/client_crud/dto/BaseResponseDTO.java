package com.ftoapanta.pichincha.client_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDTO {

    private Object data;
    private boolean success;
    private String message;
    private String error;
    private Long totalElements;

}

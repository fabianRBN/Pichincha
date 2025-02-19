package com.ftoapanta.pichincha.account_crud.strem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEvent {

    private String action;
    private Object data;
}

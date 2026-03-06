package com.therivex1907.accessmanagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //Ayuda a evitar errores en los parametros
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T data;
}

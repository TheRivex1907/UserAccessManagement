package com.therivex1907.accessmanagement.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //Ayuda a evitar errores en los parametros
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private LocalDateTime operation_time;
}

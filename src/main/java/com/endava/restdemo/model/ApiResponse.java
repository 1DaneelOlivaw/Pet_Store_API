package com.endava.restdemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Integer code;
    private String type;
    private String message;
}

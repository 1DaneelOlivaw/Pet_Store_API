package com.endava.restdemo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Order {

    private Long id;
    private Long petId;
    private Integer quantity;
    private LocalDateTime shipDate;
    private String status;
    private boolean complete;

    public Order() {
        shipDate = LocalDateTime.now();
    }

}

package com.endava.restdemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Category {
    private Long id;


    @Size(min = 4, max = 50)
    private String name;


}

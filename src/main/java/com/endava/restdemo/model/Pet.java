package com.endava.restdemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pet {
    private Long id;
    private Category category;

    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    @NotNull
    private List<String> photoUrls = new ArrayList<>();

    private List<Tag> tags = new ArrayList<>();

    private String status;
}

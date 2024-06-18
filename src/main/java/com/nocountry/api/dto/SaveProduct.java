package com.nocountry.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter @Setter
public class SaveProduct implements Serializable {

    @NotBlank (message = "El nombre no puede estar vacio")
    private String name;

    private String description;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    private String image;

    @Min(value = 0 , message = "El stock debe ser mayor a 0")
    private int stock;

    @Min(value = 1, message = "El tiempo debe ser mayor a 1")
    private int time;

    private Set<String> categories;

}

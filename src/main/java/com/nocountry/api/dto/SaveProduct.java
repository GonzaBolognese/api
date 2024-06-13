package com.nocountry.api.dto;

import com.nocountry.api.persistence.entity.Category.CategoryType;
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

    @NotBlank
    private String name;

    private String description;

    @DecimalMin(value = "0.01")
    private BigDecimal price;

    private String image;

    @Min(value = 0)
    private int stock;

    @Min(value = 1)
    private int time;

    private Set<String> categories;

}

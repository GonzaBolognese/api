package com.nocountry.api.dto;

import com.nocountry.api.persistence.entity.Category.CategoryType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class SaveCategory implements Serializable {

    @NotBlank(message = "Category name is required")
    private CategoryType name;
}

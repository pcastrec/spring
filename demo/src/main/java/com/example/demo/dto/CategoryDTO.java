package com.example.demo.dto;

import com.example.demo.model.Category;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id = null;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 100, message = "Le nom doit contenir entre 3 et 100 caracteres")
    private String name;

    @Size(max = 500, message = "La description ne peut pas depasser 500 caracteres")
    private String description;

    @NotNull(message = "La categorie est obligatoire")
    private Category category;

}

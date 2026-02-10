package com.example.demo.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDTO {
    
    private Long id = null;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 100, message = "Le nom doit contenir entre 3 et 100 caracteres")
    private String name;

    @Size(max = 500, message = "La description ne peut pas depasser 500 caracteres")
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit etre positif")
    private BigDecimal price;

    @NotNull(message = "Le stock est obligatoire")
    @Min(value = 0, message = "Le stock ne peut pas etre negatif")
    private Integer stock;
}
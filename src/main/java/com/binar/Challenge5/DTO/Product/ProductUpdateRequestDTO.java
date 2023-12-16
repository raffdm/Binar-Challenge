package com.binar.Challenge5.DTO.Product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateRequestDTO {

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    private BigDecimal price;

    @NotBlank(message = "active cant be blank")
    private boolean active;

}

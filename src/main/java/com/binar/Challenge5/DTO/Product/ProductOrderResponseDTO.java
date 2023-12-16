package com.binar.Challenge5.DTO.Product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductOrderResponseDTO {

    private String productCode;

    private String productName;

    private BigDecimal price;

    private String merchantCode;

    private String merchantName;

    private int qty;

}

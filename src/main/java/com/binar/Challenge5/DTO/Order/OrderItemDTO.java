package com.binar.Challenge5.DTO.Order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrderItemDTO {

    @NotBlank(message = "product code cant null")
    private String productCode;

    @NotBlank(message = "quantity cant null")
    private int quantity;

}

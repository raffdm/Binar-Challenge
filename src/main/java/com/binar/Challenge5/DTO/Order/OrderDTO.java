package com.binar.Challenge5.DTO.Order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    @NotBlank(message = "user code cant null")
    private String userCode;

    private String destinationAddress;

    @NotBlank(message = "order item cant null")
    private List<OrderItemDTO> orderItem;
}

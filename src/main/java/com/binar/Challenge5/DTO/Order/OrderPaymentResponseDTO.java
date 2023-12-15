package com.binar.Challenge5.DTO.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentResponseDTO {

    private String orderCode;

    private String username;

    private String address;

    private String orderTime;

    private String payment;

    private OrderDetailResponseDTO orderDetailResponseDTO;

}

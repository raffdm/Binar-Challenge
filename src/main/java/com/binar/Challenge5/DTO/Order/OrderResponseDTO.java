package com.binar.Challenge5.DTO.Order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponseDTO {

    private String orderCode;

    private String username;

    private String address;

    private Date orderTime;

    private String payment;

    private OrderDetailResponseDTO orderDetailResponseDTO;

}

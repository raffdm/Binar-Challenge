package com.binar.Challenge5.DTO.Order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderGetResponseDTO {

    private String orderCode;

    private String address;

    private Date orderTime;

    private String paymentStatus;

    private OrderDetailResponseDTO orderDetailResponseDTO;

}

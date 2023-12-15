package com.binar.Challenge5.Service;

import com.binar.Challenge5.DTO.Order.OrderDTO;
import com.binar.Challenge5.DTO.Order.OrderGetResponseDTO;
import com.binar.Challenge5.DTO.Order.OrderPaymentResponseDTO;
import com.binar.Challenge5.DTO.Order.OrderResponseDTO;
import com.binar.Challenge5.DTO.ResponseHandling;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface OrderService {
    ResponseHandling<OrderResponseDTO> createOrder(OrderDTO orderDTO);

    ResponseHandling<OrderPaymentResponseDTO> payment(String code) throws FileNotFoundException, JRException;

    ResponseHandling<List<OrderGetResponseDTO>> getOrder(String code);
}

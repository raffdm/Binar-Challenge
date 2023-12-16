package com.binar.Challenge5.Service.Impl;

import com.binar.Challenge5.DTO.CustomFileDTO;
import com.binar.Challenge5.DTO.Order.*;
import com.binar.Challenge5.DTO.Product.ProductOrderResponseDTO;
import com.binar.Challenge5.DTO.ResponseHandling;
import com.binar.Challenge5.Controller.Entity.Order;
import com.binar.Challenge5.Controller.Entity.OrderDetail;
import com.binar.Challenge5.Controller.Entity.Product;
import com.binar.Challenge5.Controller.Entity.User;
import com.binar.Challenge5.Repo.OrderRepository;
import com.binar.Challenge5.Repo.ProductRepository;
import com.binar.Challenge5.Repo.UserRepository;
import com.binar.Challenge5.Service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;



    @Override
    public ResponseHandling<OrderResponseDTO> createOrder(OrderDTO orderDTO) {
        ResponseHandling<OrderResponseDTO> response = new ResponseHandling<>();
        Optional<User> user = userRepository.findByUserCode(orderDTO.getUserCode());
        if (!user.isPresent()) {
            response.setMessage("fail to create order");
            response.setErrors("user code not found");
            return response;
        }
        Order order = new Order();
        order.setOrderCode(getKode());
        order.setOrderTime(new Date());
        order.setCompleted(false);
        order.setDestinationAddress(orderDTO.getDestinationAddress());

        List<OrderDetail> orderDetails = getOrderDetails(orderDTO);

        if (orderDetails.isEmpty() || orderDetails == null) {
            response.setMessage("fail to order");
            response.setErrors("product not found");
            return response;
        }

        order.setOrderDetail(orderDetails);

        order.setUser(user.get());
        orderRepository.save(order);

        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setOrderCode(order.getOrderCode());
        responseDTO.setUsername(order.getUser().getUsername());
        responseDTO.setAddress(order.getDestinationAddress());
        responseDTO.setOrderTime(order.getOrderTime());
        responseDTO.setPayment("unpaid");

        List<ProductOrderResponseDTO> productResponseDTOS = getProductOrderResponseDTOS(orderDetails);

        int qty = getQty(orderDetails);

        BigDecimal totalPrice = getBigDecimal(orderDetails);

        OrderDetailResponseDTO orderDetailResponseDTO = new OrderDetailResponseDTO();
        orderDetailResponseDTO.setTotalPrice(totalPrice);
        orderDetailResponseDTO.setQuantity(qty);
        orderDetailResponseDTO.setProductOrderResponseDTO(productResponseDTOS);
        responseDTO.setOrderDetailResponseDTO(orderDetailResponseDTO);
        response.setMessage("success to order");
        response.setData(responseDTO);
        return response;
    }

    @Override
    public ResponseHandling<OrderPaymentResponseDTO> payment(String code) throws FileNotFoundException, JRException {
        Optional<Order> order = orderRepository.findByOrderCode(code);
        ResponseHandling<OrderPaymentResponseDTO> response = new ResponseHandling<>();
        if (!order.isPresent()){
            response.setMessage("fail to pay");
            response.setErrors("code not found");
            return response;
        }else if (order.get().getCompleted() == true){
            response.setMessage("fail to pay");
            response.setErrors("payment already successfull");
            return response;
        }
        Order order1 = order.get();
        order1.setCompleted(true);
        orderRepository.save(order1);

        OrderPaymentResponseDTO orderPaymentResponse = new OrderPaymentResponseDTO();
        orderPaymentResponse.setOrderCode(order1.getOrderCode());
        orderPaymentResponse.setUsername(order1.getUser().getUsername());
        orderPaymentResponse.setAddress(order1.getDestinationAddress());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String formattedOrderTime = sdf.format(order1.getOrderTime());
        orderPaymentResponse.setOrderTime(formattedOrderTime);
        orderPaymentResponse.setPayment("paid");

        List<ProductOrderResponseDTO> productOrderResponseDTOS = getProductOrderResponseDTOS(order1);

        int qty = getAnInt(productOrderResponseDTOS);
        BigDecimal totalPrice = getTotalPrice(productOrderResponseDTOS);

        OrderDetailResponseDTO responseDTO = new OrderDetailResponseDTO();
        responseDTO.setQuantity(qty);
        responseDTO.setTotalPrice(totalPrice);
        responseDTO.setProductOrderResponseDTO(productOrderResponseDTOS);
        orderPaymentResponse.setOrderDetailResponseDTO(responseDTO);
        response.setData(orderPaymentResponse);
        response.setMessage("successfully paid the product !!!");

        //jasper
        File file = ResourceUtils.getFile("classpath:recipt.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        List<CustomFileDTO> customFileDTOList = new ArrayList<>();
        CustomFileDTO customFileDTO = new CustomFileDTO();
        customFileDTO.setOrderCode(orderPaymentResponse.getOrderCode());
        customFileDTO.setUsername(orderPaymentResponse.getUsername());
        customFileDTO.setAddress(orderPaymentResponse.getAddress());
        customFileDTO.setPayment(orderPaymentResponse.getPayment());
        customFileDTO.setOrderTime(orderPaymentResponse.getOrderTime());
        customFileDTO.setQuantity(responseDTO.getQuantity());
        customFileDTO.setTotalPrice(responseDTO.getTotalPrice());


        List<CustomFileDTO> customFileProduct = getCustomFileDTOS(productOrderResponseDTOS);


        customFileDTOList.add(customFileDTO);
        customFileDTOList.addAll(customFileProduct);

        jasperBuild(jasperReport, customFileDTOList);

        return response;
    }

    private void jasperBuild(JasperReport jasperReport, List<CustomFileDTO> customFileDTOList) throws JRException {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customFileDTOList);
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("created By", "Arda");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Binar\\pdf\\recipt.pdf");
    }

    private List<CustomFileDTO> getCustomFileDTOS(List<ProductOrderResponseDTO> productOrderResponseDTOS) {
        List<CustomFileDTO> customFileProduct = productOrderResponseDTOS.stream().map((p)->{
            CustomFileDTO customFileProductDTO = new CustomFileDTO();
            customFileProductDTO.setProductCode(p.getProductCode());
            customFileProductDTO.setProductName(p.getProductName());
            customFileProductDTO.setPrice(p.getPrice());
            customFileProductDTO.setMerchantName(p.getMerchantName());
            customFileProductDTO.setQty(p.getQty());
            return customFileProductDTO;
        }).collect(Collectors.toList());
        return customFileProduct;
    }

    @Override
    public ResponseHandling<List<OrderGetResponseDTO>> getOrder(String usercode) {
        Optional<List<Order>> order = orderRepository.findByUserCode(usercode);
        ResponseHandling<List<OrderGetResponseDTO>> response = new ResponseHandling<>();
        if (!order.isPresent()){
            response.setMessage("fail to get data");
            response.setErrors("user with code " +usercode+ " not found");
            return response;
        }
        List<Order> orderList = order.get();
        List<OrderGetResponseDTO> orders = orderList.stream().map((p)->{
            OrderGetResponseDTO responseDTO = new OrderGetResponseDTO();
            responseDTO.setOrderCode(p.getOrderCode());
            responseDTO.setAddress(p.getDestinationAddress());
            responseDTO.setOrderTime(p.getOrderTime());
            responseDTO.setPaymentStatus(p.getCompleted() ? "Paid" : "Unpaid");

            List<ProductOrderResponseDTO> productOrderResponse = new ArrayList<>();
            for (OrderDetail orderDetail : p.getOrderDetail()){
                ProductOrderResponseDTO responseDTO1 = new ProductOrderResponseDTO();
                responseDTO1.setProductCode(orderDetail.getProduct().getProductCode());
                responseDTO1.setProductName(orderDetail.getProduct().getProductName());
                responseDTO1.setPrice(orderDetail.getProduct().getPrice());
                responseDTO1.setMerchantCode(orderDetail.getProduct().getMerchant().getMerchCode());
                responseDTO1.setMerchantName(orderDetail.getProduct().getMerchant().getMerchName());
                responseDTO1.setQty(orderDetail.getQty());
                productOrderResponse.add(responseDTO1);
            }

            OrderDetailResponseDTO orderDetailResponse = new OrderDetailResponseDTO();
            int qty = 0;
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (OrderDetail orderDetail : p.getOrderDetail()){
                qty += orderDetail.getQty();
                BigDecimal quantity = new BigDecimal(orderDetail.getQty());
                totalPrice.multiply(quantity);
            }
            orderDetailResponse.setQuantity(qty);
            orderDetailResponse.setTotalPrice(totalPrice);
            orderDetailResponse.setProductOrderResponseDTO(productOrderResponse);
            responseDTO.setOrderDetailResponseDTO(orderDetailResponse);
            return responseDTO;
        }).collect(Collectors.toList());

        response.setData(orders);
        response.setMessage("success get data");
        return response;
    }

    private List<ProductOrderResponseDTO> getProductOrderResponseDTOS(Order order1) {
        List<ProductOrderResponseDTO> productOrderResponseDTOS = order1.getOrderDetail().stream().map((p)->{
            ProductOrderResponseDTO responseDTO = new ProductOrderResponseDTO();
            responseDTO.setProductCode(p.getProduct().getProductCode());
            responseDTO.setProductName(p.getProduct().getProductName());
            responseDTO.setPrice(p.getProduct().getPrice());
            responseDTO.setMerchantCode(p.getProduct().getMerchant().getMerchCode());
            responseDTO.setMerchantName(p.getProduct().getMerchant().getMerchName());
            responseDTO.setQty(p.getQty());
            return responseDTO;
        }).collect(Collectors.toList());
        return productOrderResponseDTOS;
    }

    private BigDecimal getTotalPrice(List<ProductOrderResponseDTO> productOrderResponseDTOS) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductOrderResponseDTO responseDTO: productOrderResponseDTOS){
            BigDecimal productPrice = responseDTO.getPrice();
            BigDecimal quantity = new BigDecimal(responseDTO.getQty());
            BigDecimal total = productPrice.multiply(quantity);
            totalPrice = totalPrice.add(total);
        }
        return totalPrice;
    }

    private int getAnInt(List<ProductOrderResponseDTO> productOrderResponseDTOS) {
        int qty = 0;
        for (ProductOrderResponseDTO res : productOrderResponseDTOS){
            qty += res.getQty();
        }
        return qty;
    }

    private List<OrderDetail> getOrderDetails(OrderDTO orderDTO) {
        return orderDTO.getOrderItem().stream().map((p) -> {
            Optional<Product> product = productRepository.findByProductCode(p.getProductCode());
            Product product1 = product.get();
            if (!product.isPresent()) {
                return null;
            }
            BigDecimal priceTotal = product.get().getPrice().multiply(BigDecimal.valueOf(p.getQuantity()));
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQty(p.getQuantity());
            orderDetail.setTotalPrice(priceTotal);
            orderDetail.setProduct(product1);
            return orderDetail;
        }).collect(Collectors.toList());
    }

    private List<ProductOrderResponseDTO> getProductOrderResponseDTOS(List<OrderDetail> orderDetails) {
        List<ProductOrderResponseDTO> productResponseDTOS = orderDetails.stream().map((p) -> {
            ProductOrderResponseDTO productOrderResponseDTO = new ProductOrderResponseDTO();
            productOrderResponseDTO.setProductCode(p.getProduct().getProductCode());
            productOrderResponseDTO.setProductName(p.getProduct().getProductName());
            productOrderResponseDTO.setPrice(p.getProduct().getPrice());
            productOrderResponseDTO.setMerchantCode(p.getProduct().getMerchant().getMerchCode());
            productOrderResponseDTO.setMerchantName(p.getProduct().getMerchant().getMerchName());
            return productOrderResponseDTO;
        }).collect(Collectors.toList());
        return productResponseDTOS;
    }

    private BigDecimal getBigDecimal(List<OrderDetail> orderDetails) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderDetail detail : orderDetails) {
            BigDecimal productPrice = detail.getProduct().getPrice();
            BigDecimal quantity = new BigDecimal(detail.getQty());
            BigDecimal total = productPrice.multiply(quantity);
            totalPrice = totalPrice.add(total);
        }
        return totalPrice;
    }

    private int getQty(List<OrderDetail> orderDetails) {
        int qty = 0;
        for (OrderDetail orderDetail : orderDetails) {
            qty += orderDetail.getQty();
        }
        return qty;
    }

    private String getKode() {
        UUID uuid = UUID.randomUUID();
        String kode = uuid.toString().substring(0, 5);
        return kode;
    }

}

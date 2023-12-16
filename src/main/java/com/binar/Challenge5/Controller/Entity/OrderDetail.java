package com.binar.Challenge5.Controller.Entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_detail")
@Data
public class OrderDetail {

    @Id
    @GenericGenerator(
            strategy = "uuid2",
            name = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(nullable = false)
    private Integer qty;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "id")
    private Product product;

}

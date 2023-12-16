package com.binar.Challenge5.Controller.Entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            strategy = "uuid2",
            name = "uuid")
    private String id;

    @Column(
            name = "productCode",
            length = 5,
            nullable = false,
            unique = true)
    private String productCode;

    @Column(
            name = "productName",
            length = 32,
            nullable = false)
    private String productName;

    @Column(
            name = "productPrice",
            nullable = false)
    private BigDecimal price;

    private Boolean active;

    @ManyToOne
    @JoinColumn(
            name = "merchant_id",
            nullable = false,
            referencedColumnName = "id")
    private Merchant merchant;

}

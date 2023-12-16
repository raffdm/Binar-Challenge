package com.binar.Challenge5.Controller.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "merchant")
@Data
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "merch_code",
            length = 5,
            nullable = false,
            unique = true)
    private String merchCode;

    @Column(
            name = "merch_name",
            length = 30,
            nullable = false,
            unique = true)
    private String merchName;

    @Column(
            name = "merch_location",
            length = 100,
            nullable = false,
            unique = true)
    private String merchLocation;

    private Boolean open;

    private Boolean deleted;
}

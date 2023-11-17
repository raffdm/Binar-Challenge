package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String name;
    private int price;
    private int totalOrder;

    //constructor
    public Menu (String name, int price) {
        this.name = name;
        this.price = price;
        this.totalOrder = 0;
    }
}

package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private int totalOrder;
    private int totalPrice;

    public Order() {
        this.totalOrder = 0;
        this.totalPrice = 0;
    }

    public void addOrder(int amount, int price) {
        totalOrder += amount;
        totalPrice += (amount * price);
    }
}

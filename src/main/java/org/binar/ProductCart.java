package org.binar;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCart {
    private ProductMenu productMenu;
    private int qty;

    public ProductCart(ProductMenu productMenu, int qty) {
        this.productMenu = productMenu;
        this.qty = qty;
    }

    public ProductMenu getProduct() {
        return productMenu;
    }
}

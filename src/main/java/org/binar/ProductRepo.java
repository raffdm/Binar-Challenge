package org.binar;

import java.util.Arrays;
import java.util.List;

public class ProductRepo {
    public static List<ProductMenu> menuList() {
        ProductMenu menu1 = new ProductMenu(1, "Nasi Goreng", 15000);
        ProductMenu menu2 = new ProductMenu(2, "Mie Goreng", 20000);
        ProductMenu menu3 = new ProductMenu(3, "Nasi + Ayam", 12000);
        ProductMenu menu4 = new ProductMenu(4, "Es Teh Manis", 10000);
        ProductMenu menu5 = new ProductMenu(5, "Es Jeruk\t", 12000);

        return Arrays.asList(menu1, menu2, menu3, menu4, menu5);
    }
}

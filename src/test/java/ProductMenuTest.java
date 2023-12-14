import org.binar.ProductMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ProductMenuTest {
    ProductMenu productMenu;

    @BeforeEach
    void setUp() {
        System.out.println("Unit Testing Product Menu");
    }

    @Test
    void ProductMenu__TestOptional(){
        productMenu = new ProductMenu();
        String namaProduk = Optional.ofNullable(productMenu.getNamaMenu())
                .orElse("Data berupa null");
        Assertions.assertEquals("Data berupa null", namaProduk);
    }

    @Test
    void ProdukMenuTest__Success(){
        productMenu = new ProductMenu(1, "Nasi Goreng", 15000);
        Assertions.assertEquals(1, productMenu.getNomorMenu());
        Assertions.assertEquals("Nasi Goreng", productMenu.getNamaMenu());
        Assertions.assertEquals(15000, productMenu.getHargaMenu());
        Assertions.assertNotNull(productMenu);
    }

    @Test
    void ProdukMenuTest__Failed(){
        productMenu = null;
        Assertions.assertThrows(NullPointerException.class, () -> productMenu.setNamaMenu(null));
    }
}
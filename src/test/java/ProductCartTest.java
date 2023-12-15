import org.binar.ProductCart;
import org.binar.ProductMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductCartTest {

    ProductCart produkCart;
    ProductMenu productMenu;

    @BeforeEach
    void setUp() {
        productMenu = new ProductMenu();
        System.out.println("Unit Testing Product Cart");
    }

    @Test
    void ProductCart_Test_Success(){
        produkCart = new ProductCart(new ProductMenu
                (1, "Nasi Goreng", 15000), 2);
        Assertions.assertEquals(2, produkCart.getQty());
        Assertions.assertEquals(15000, produkCart.getProduct().getHargaMenu());
        Assertions.assertEquals("Nasi Goreng", produkCart.getProduct().getNamaMenu());
        Assertions.assertNotNull(produkCart);
    }

    @Test
    void ProductCart_Test_Failed(){
        Assertions.assertThrows(NullPointerException.class, () -> produkCart.setProductMenu(null));
    }
}
import org.binar.ProductMenu;
import org.binar.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductRepoTest {
    List<ProductMenu> menuList;

    @BeforeEach
    void setUp() {
        System.out.println("Unit Testing Product Repository");
    }

    @Test
    void DaftarMenu_Test_Success(){
        menuList = ProductRepo.menuList();
        Assertions.assertEquals(5, menuList.size());
        Assertions.assertEquals(1, menuList.get(0).getNomorMenu());
        Assertions.assertEquals("Nasi Goreng", menuList.get(0).getNamaMenu());
        Assertions.assertEquals(15000, menuList.get(0).getHargaMenu());
        Assertions.assertNotNull(menuList);
    }

    @Test
    void DaftarMenu_Test_Failed(){
        Assertions.assertThrows(NullPointerException.class, () -> menuList.get(0).setNomorMenu(null));
    }
}

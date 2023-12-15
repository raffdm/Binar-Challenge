import org.binar.OrderService;
import org.binar.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class OrderServiceImplTest {
    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl();
        System.out.println("Unit Testing Order Service Implement");
    }

    @Test
    void menuPemesanan_Test(){
        String userInput = "0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        orderService = new OrderServiceImpl();
        Assertions.assertDoesNotThrow(() -> orderService.menuPemesanan());
    }

    @Test
    void menuPembayaran_Test(){
        String userInput = "99\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        orderService = new OrderServiceImpl();
        Assertions.assertDoesNotThrow(() -> orderService.menuPembayaran());
    }

    @Test
    void cetakStruk_Test_Success() {
        String hasilFile = orderService.cetakStruk("C:/Me/UMC/STUPED/Challenge/chptr3/BinarAcademy-Challenge_3/src/main/resources/struk.txt");
        Assertions.assertEquals("C:/Me/UMC/STUPED/Challenge/chptr3/BinarAcademy-Challenge_3/src/main/resources/struk.txt", hasilFile);
        Assertions.assertNotNull(hasilFile);
    }

    @Test
    void cetakStruk_Test_Failed(){
        Assertions.assertThrows(NullPointerException.class, () -> orderService.cetakStruk(null));
    }
}
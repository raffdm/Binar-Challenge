package org.binar;

public class MenuController {
    OrderService orderService;

    public MenuController(){
        orderService = new OrderServiceImpl();
    }

    public void menuStart(){
        orderService.menuPemesanan();
        orderService.menuPembayaran();
        orderService.cetakStruk(
                "C:/Me/UMC/STUPED/Challenge/chptr3/BinarAcademy-Challenge_3/src/main/resources/struk.txt"
        );
    }
}

package org.example;

import java.util.ArrayList;

public class AllMenu {
    private ArrayList<Menu> allMenu;
    int totalMenu;

    public AllMenu() {
        this.totalMenu = 5;
        this.allMenu = new ArrayList<Menu>();

        allMenu.add(new Menu("Nasi Goreng", 15000));
        allMenu.add(new Menu("Mie Goreng", 13000));
        allMenu.add(new Menu("Nasi + Ayam", 18000));
        allMenu.add(new Menu("Es Teh Manis", 3000));
        allMenu.add(new Menu("Es Jeruk\t", 5000));
    }

    public void showAllMenu() {
        System.out.println("==========================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("==========================");
        System.out.println("Silahkan pilih makanan : ");

        int i = 1;
        for (int j = 0; j < totalMenu; j++) {
            Menu menu = allMenu.get(j);
            System.out.println(i + ". " + menu.getName() + "\t\t| " + menu.getPrice());
            i++;
        }

        System.out.println("99. Pesan Dan Bayar");
        System.out.println("0. Keluar Aplikasi");
    }

    public Menu getMenu(int i){
        return allMenu.get(i);
    }
}

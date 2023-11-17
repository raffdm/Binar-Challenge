package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private AllMenu menuList;
    private Order pesanan;

    public Main() {
        scanner = new Scanner(System.in);
        menuList = new AllMenu();
        pesanan = new Order();
    }

    public void showMainScreen() {
        boolean exit = false;

        while (!exit) {
            menuList.showAllMenu();
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 99:
                    orderAndPay();
                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    exit = true;
                    break;
                default:
                    if (pilihan >= 1 && pilihan <= menuList.totalMenu) {
                        pesanMenu(pilihan);
                    } else {
                        showError("Pilihan tidak valid");
                    }
                    break;
            }
        }
    }

    public void pesanMenu(int nomorMenu) {
        System.out.println("========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("========================");

        Menu menu = menuList.getMenu(nomorMenu - 1);
        System.out.println(menu.getName() + "\t\t| " + menu.getPrice());
        System.out.print("Jumlah: ");

        int jumlah = scanner.nextInt();

        if (jumlah > 0) {
            menu.setTotalOrder(jumlah);
            pesanan.addOrder(jumlah, menu.getPrice());
        } else {
            showError("Jumlah pesanan tidak valid");
        }
    }

    public void orderAndPay() {
        int jumlahOrderTotal = 0;

        System.out.println("========================");
        System.out.println("Konfirmasi dan Pembayaran");
        System.out.println("========================");

        for (int i = 0; i < menuList.totalMenu; i++) {
            Menu menu = menuList.getMenu(i);
            int jumlahOrder = menu.getTotalOrder();

            if (jumlahOrder > 0) {
                int subtotal = menu.getPrice() * jumlahOrder;

                System.out.println(menu.getName() + "\t\t" + jumlahOrder + "\tRp. " + subtotal);
            }
            jumlahOrderTotal += jumlahOrder;
        }
        boolean exit = false;

        while (!exit) {

            System.out.println("---------------------------------+");
            System.out.println("Total\t\t\t" +jumlahOrderTotal + "\tRp. " + pesanan.getTotalPrice());
            System.out.println("1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali Ke Menu Utama");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih: ");

            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tampilkanOrder();
                    cetakStrukTransaksi();
                    exit = true;
                    break;
                case 2:
                    exit = true;
                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                    break;
                default:
                    showError("Pilihan tidak valid");
                    break;
            }
        }
    }

    public void cetakStrukTransaksi() {
        int jumlahOrderTotal = 0;

        System.out.println("========================");
        System.out.println("Cetak Struk Transaksi");
        System.out.println("========================");

        try {
            FileWriter fileWriter = new FileWriter("receipt.txt");
            fileWriter.write("========================\n");
            fileWriter.write("Struk BinarFud\n");
            fileWriter.write("========================\n");

            for (int i = 0; i < menuList.totalMenu; i++) {
                Menu menu = menuList.getMenu(i);
                int jumlahOrder = menu.getTotalOrder();

                if (jumlahOrder > 0) {
                    int subtotal = menu.getPrice() * jumlahOrder;

                    fileWriter.write(menu.getName() + "\t" + jumlahOrder + "\tRp. " + subtotal + "\n");
                }
                jumlahOrderTotal += jumlahOrder;
            }

            fileWriter.write("------------------------+\n");
            fileWriter.write("Total\t\t" + jumlahOrderTotal + "\tRp " + pesanan.getTotalPrice() + "\n\n");
            fileWriter.write("Pembayaran : BinarCash\n");
            fileWriter.write("========================\n");
            fileWriter.write("Simpan struk ini sebagai\nbukti pembayaran\n");
            fileWriter.write("========================\n");
            fileWriter.close();

            System.out.println("Struk transaksi berhasil dicetak!");
        } catch (IOException e) {
            showError(e.getMessage());
        }
    }

    public void tampilkanOrder() {
        int jumlahOrderTotal = 0;

        System.out.println("========================");
        System.out.println("       Order Anda       ");
        System.out.println("========================");

        for (int i = 0; i < menuList.totalMenu; i++) {
            Menu menu = menuList.getMenu(i);
            int jumlahOrder = menu.getTotalOrder();

            if (jumlahOrder > 0) {
                System.out.println(menu.getName() + "\t" + jumlahOrder + "\tRp. " + (jumlahOrder * menu.getPrice()));
            }
        }

        System.out.println("------------------------+");
        System.out.println("Total\t\t" + jumlahOrderTotal + "\tRp " + pesanan.getTotalPrice());
        System.out.println("========================");
        System.out.println("Simpan struk ini sebagai\nbukti pembayaran");
        System.out.println("========================\n\n");

    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.showMainScreen();
    }
}

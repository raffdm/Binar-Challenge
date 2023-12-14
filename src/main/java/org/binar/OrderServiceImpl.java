package org.binar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderServiceImpl implements OrderService{
    private List<ProductMenu> menuList;
    private final List<ProductCart> listCart;
    private final Scanner scanner;
    int pilihan;
    int jmlPesanan = 0;
    Integer total;

    public OrderServiceImpl(){
        menuList = new ArrayList<>();
        menuList = ProductRepo.menuList();
        listCart = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    @Override
    public void menuPemesanan() {
        try {
            do {
                System.out.println("-----------------------------------------------");
                System.out.println("|        Selamat datang di Binar Food!        |");
                System.out.println("-----------------------------------------------");
                System.out.println("Silakan pilih menu yang ingin dipesan:");
                menuList.forEach(
                        produkMenu -> System.out.println(produkMenu.getNomorMenu()
                                + ". "
                                + produkMenu.getNamaMenu()
                                + "\t | Rp."
                                + produkMenu.getHargaMenu())
                );
                System.out.println("99. Pesan dan Bayar");
                System.out.println("0. Keluar Aplikasi");
                System.out.println();
                System.out.print("Masukkan Nomor Pesanan: ");
                pilihan = scanner.nextInt();

                if (pilihan >= 1 && pilihan <= menuList.size()) {
                    System.out.print("Masukkan Jumlah Pesanan: ");
                    jmlPesanan = scanner.nextInt();
                }
                switch (pilihan) {
                    case 0:
                        System.out.println("Terima Kasih");
                        System.exit(0);
                        break;
                    case 1:
                        ProductMenu productMenu = menuList.get(0);
                        ProductCart productCart = new ProductCart(productMenu, jmlPesanan);
                        listCart.add(productCart);
                        break;
                    case 2:
                        productMenu = menuList.get(1);
                        productCart = new ProductCart(productMenu, jmlPesanan);
                        listCart.add(productCart);
                        break;
                    case 3:
                        productMenu = menuList.get(2);
                        productCart = new ProductCart(productMenu, jmlPesanan);
                        listCart.add(productCart);
                        break;
                    case 4:
                        productMenu = menuList.get(3);
                        productCart = new ProductCart(productMenu, jmlPesanan);
                        listCart.add(productCart);
                        break;
                    case 5:
                        productMenu = menuList.get(4);
                        productCart = new ProductCart(productMenu, jmlPesanan);
                        listCart.add(productCart);
                        break;
                    case 99:
                        break;
                    default:
                        System.out.println("Nomor yang anda masukkan tidak tersedia, silahkan coba lagi!");
                        break;
                }
            } while (pilihan != 99);
        } catch (InputMismatchException ime) {
            System.out.println("Input harus berupa angka!");
        }
    }

    @Override
    public void menuPembayaran() {
        System.out.println("-------------------------------------------------");
        System.out.println("|                   PEMBAYARAN                  |");
        System.out.println("-------------------------------------------------");
        System.out.println("No\t" + "\tPesanan" + "\t\t\tHarga" + "\t  Jumlah");
        total = listCart.stream()
                .map(productCart -> {
                    System.out.println(productCart.getProduct().getNomorMenu()
                            + "\t | "
                            + productCart.getProduct().getNamaMenu()
                            + "\t | Rp."
                            + productCart
                            .getProduct().getHargaMenu()
                            + "\t | "
                            + productCart.getQty());
                    return productCart;
                })
                .reduce(0, (result, order) -> (int) (result + (order.getProduct().getHargaMenu() *
                        order.getQty())), Integer::sum);
        System.out.println();
        System.out.println("Total Pesanan: Rp." + total);
    }

    @Override
    public String cetakStruk(String Struk) {
        try {
            File file = new File(Struk);
            if (file.createNewFile()) {
                System.out.println("Struk Berhasil Dibuat");
            }
            FileWriter fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("\t\t\tStruk Pembayaran\t\n");
                bw.newLine();
                bw.write("No\t" + "\tPesanan" + "\t\t\tHarga" + "\t  Jumlah\n");
                total = listCart.stream()
                        .map(produkKeranjang -> {
                            try {
                                bw.write(produkKeranjang.getProduct().getNomorMenu()
                                        + "\t | "
                                        + produkKeranjang.getProduct().getNamaMenu()
                                        + "\t | "
                                        + produkKeranjang.getProduct().getHargaMenu()
                                        + "\t | "
                                        + produkKeranjang.getQty()
                                        + "\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            return produkKeranjang;
                        })
                        .reduce(0, (result, order) -> (int) (result + (order.getProduct().getHargaMenu() *
                                order.getQty())), Integer::sum);
                bw.newLine();
                bw.write("Total Pesanan: " + total);
                bw.newLine();
                bw.write("Terima kasih sampai jumpa kembali");
                bw.flush();
            }
            System.out.println();
            System.out.println("Terima kasih sampai jumpa kembali");
        } catch (IOException e) {
            System.out.println("Struk gagal dibuat");
        }
        return Struk;
    }
}
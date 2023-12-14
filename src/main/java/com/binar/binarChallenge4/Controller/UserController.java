//package com.binar.binarChallenge4.Controller;
//
//import com.binar.binarChallenge4.DTO.DTOMidle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//@Component
//public class UserController {
//
//    @Autowired
//    private ProductService productService;
//
//    private Scanner scanner = new Scanner(System.in);
//
//    public DTOMidle DTOmidle = new DTOMidle();
//
//    Map<String, Integer> produkk = new HashMap<>();
//
//    public void userMainMenu(){
//        boolean ulang = true;
//        while (ulang) {
//            System.out.println("========================");
//            System.out.println("=======Menu User=======");
//            System.out.println("========================");
//            System.out.println("Selamat datang " + DTOmidle.getData().getUsername());
//            System.out.println("1. Cari Merchant");
//            System.out.println("2. Cari Produk Dan Beli");
//            System.out.println("3. Keluar Dari Aplikasi");
//            System.out.print("Pilih : ");
//            int pilih = scanner.nextInt();
//
//            if (pilih == 1) {
//                ulang = false;
//                searchMerchant();
//            }else if (pilih == 2){
//                ulang = false;
//                System.out.println("Anda memilih Menu produk");
//                searchProduct();
//            }else if (pilih == 3){
//                ulang = false;
//                System.out.println("Terima Kasih Telah Menggunakan Aplikasi Kami ");
//            }else {
//                System.out.println("maaf kode yang anda masukan tidak sesuai !!");
//            }
//        }
//    }
//
//}

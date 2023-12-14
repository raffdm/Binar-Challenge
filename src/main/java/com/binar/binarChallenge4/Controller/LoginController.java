package com.binar.binarChallenge4.Controller;


import com.binar.binarChallenge4.DTO.DTOUserLogin;
import com.binar.binarChallenge4.Entity.User;
import com.binar.binarChallenge4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LoginController {

    @Autowired
    private UserService userService;

    private Scanner scanner = new Scanner(System.in);

    @PostConstruct
    public void init() throws ParseException {
        pilihAuth();
    }

    public void pilihAuth(){
        boolean ulang = true;
        while (ulang) {
            System.out.println("\nSelamat Datang! \nSilahkan pilih menu dibawah ini: ");
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("3. Keluar");
            System.out.print("pilih : ");
            try {
                int pilih = scanner.nextInt();
                scanner.nextLine();

                if (pilih == 1) {
                    ulang = false;
                    login();
                } else if (pilih == 2) {
                    ulang = false;
                    registrasi();
                } else if(pilih == 3){
                    ulang = false;
                    System.out.println("TERIMA KASIH");
                    System.exit(0);
                } else {
                    System.out.println("Maaf kode yang anda masukkan tidak sesuai!!");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Maaf, harap masukkan angka yang sesuai.");
            }
        }
    }

    public void login(){
        boolean ulang = true;
        while (ulang){
            System.out.println("\n---  LOGIN  ---");
            System.out.print("Masukan Username : ");
            String username = scanner.next();
            System.out.print("Masukan Password : ");
            String pass = scanner.next();

            if (username.isEmpty() || pass.isEmpty()){
                System.out.println("Gagal Melakukan login, shilakan coba lagi!!!");
                pilihAuth();
            }else {
                DTOUserLogin DTOuserLogin = DTOUserLogin.builder()
                        .username(username)
                        .password(pass)
                        .build();
                Optional<User> returnn = userService.login(DTOuserLogin);

                if (!returnn.isPresent() || returnn.equals(null) || returnn == null) {
                    System.out.println("Username/Password anda salah!!!");
                    login();
                } else {
                    System.out.println("||======================||");
                    System.out.println("|| Anda Berhasil Login! ||");
                    System.out.println("||======================||");
                    pilihAuth();
//                    System.exit(0);
//                    if (returnn.get().getRole().equals(Role.ADMIN)){
//                        ulang = false;
//                        adminController.adminMainMenu();
//                    }else {
//                        ulang = false;
//                        User user  = returnn.get();
//                        userController.midleDTO.setData(user);
//                        userController.userMainMenu();
//                    }

                }
            }
        }
    }

    public void registrasi(){
        System.out.println("\n---  REGISTRASI  ---");
        System.out.print("Masukan Username: ");
        String username = scanner.next();
        System.out.print("Masukan Email   : ");
        String email = scanner.next();
        System.out.print("Masukan Password: ");
        String pass = scanner.next();

        regex(email);

        if (username.isEmpty() || email.isEmpty() || pass.isEmpty()){
            System.out.println("Gagal Melakukan registrasi, silahkan coba lagi!");
            pilihAuth();
        }else {
            String returnn = userService.register(username, email, pass);
            System.out.println(returnn);
            pilihAuth();
        }
    }

    //regex adalah fungsi untuk memvalidasi pola sesuai keinginan kita
    //dari pola dibawah ditentukan bahwa email harus berupa/mengandung
    // A-Z atau a-z atau 0-9 dilanjutkan dengan @ dan dibatasi dengan .
    private void regex(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            System.out.println("Email yang anda masukkan tidak sesuai, Mohon coba kembali1" +
                    "\n\n");
            pilihAuth();
        }
    }

}

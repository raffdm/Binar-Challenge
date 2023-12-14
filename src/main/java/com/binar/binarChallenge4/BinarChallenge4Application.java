package com.binar.binarChallenge4;

import com.binar.binarChallenge4.Controller.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.binar.binarChallenge4"})
@ComponentScan({"com.binar.binarChallenge4"})
//@ComponentScan(basePackages = "com.binar.binarChallenge4")
//@Component(basePackages = "com.binar.binarChallenge4")
public class BinarChallenge4Application {

	public static void main(String[] args) {

		SpringApplication.run(BinarChallenge4Application.class, args);

	}

}

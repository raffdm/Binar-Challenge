package com.binar.Challenge5.DTO.User;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRegisterRequestDTO {

    @NotBlank(message = "username cant be null!!!")
    private String username;

    @NotBlank(message = "email cant be null!!!")
    private String email;

    @NotBlank(message = "password cant be null!!!")
    private String password;

}

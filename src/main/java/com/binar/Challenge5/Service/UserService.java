package com.binar.Challenge5.Service;

import com.binar.Challenge5.DTO.ResponseHandling;
import com.binar.Challenge5.DTO.User.*;
import com.binar.Challenge5.DTO.*;

import java.util.List;

public interface UserService {

    UserRegisterResponseDTO register(UserRegisterRequestDTO requset);

    ResponseHandling<List<UserResponseDTO>> getUser(String name);

    ResponseHandling<UserResponseDTO> updateUser(String code, UserRequestUpdateDTO request);

    UserResponseDeleteDTO deleteData(String code);
}

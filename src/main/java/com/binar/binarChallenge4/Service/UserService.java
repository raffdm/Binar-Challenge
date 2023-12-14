package com.binar.binarChallenge4.Service;

import com.binar.binarChallenge4.DTO.DTOFindUser;
import com.binar.binarChallenge4.DTO.DTOUserLogin;
import com.binar.binarChallenge4.Entity.User;

import java.util.Optional;

public interface UserService {
    String register(String username, String email, String pass);

    Optional<User> login(DTOUserLogin DTOuserLogin);

    Boolean createUser(String id);

    DTOFindUser findUser(String username);

    Boolean deleteUser(String id);
}


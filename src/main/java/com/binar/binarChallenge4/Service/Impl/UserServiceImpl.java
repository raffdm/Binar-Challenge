package com.binar.binarChallenge4.Service.Impl;

import com.binar.binarChallenge4.DTO.DTOFindUser;
import com.binar.binarChallenge4.DTO.DTOUserLogin;
import com.binar.binarChallenge4.Entity.Role;
import com.binar.binarChallenge4.Entity.User;
import com.binar.binarChallenge4.Repo.UserRepo;
import com.binar.binarChallenge4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public String register(String username, String email, String pw) {
        logger.info("Registering user: {}", username);

        User user = User.builder()
                .username(username)
                .email(email)
                .password(pw)
                .role(Role.USER)
                .build();

        if (userRepo.existsByUsername(username) || userRepo.existsByEmail(email)){
            logger.warn("Username/Email has already been used.");

            return "Username/Email telah digunakan user lain. Coba lagi";
        } else {
            userRepo.save(user);
            logger.info("User registered: {}", user);

            return "Data Berhasil Disimpan Silahkan Login";
        }

    }

    @Override
    public Optional<User> login(DTOUserLogin DTOuserLogin) {
        logger.info("User login: {}", DTOuserLogin.getUsername());

        try {
            Optional<User> user = userRepo.findByUsername(DTOuserLogin.getUsername());
            if (user.isPresent()){
                User user2 = user.get();
                if (user2.getPassword().equals(DTOuserLogin.getPassword())){
                    logger.info("User logged in: {}", user2.getUsername());

                    return Optional.of(user2);
                }
            }
            logger.warn("Login failed for user: {}", DTOuserLogin.getUsername());

            return Optional.empty();
        }catch (NullPointerException e){
            logger.error("Error while logging in: {}", e.getMessage());

            e.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public Boolean createUser(String id) {
        logger.info("Creating admin user with ID: {}", id);

        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            User user1 = user.get();
            user1.setRole(Role.ADMIN);
            userRepo.save(user1);
            logger.info("Admin user created: {}", user1.getUsername());

            return true;
        }
        logger.warn("Failed to create admin user because user not found.");

        return false;
    }

    @Override
    public DTOFindUser findUser(String username) {
        logger.info("Finding user by username: {}", username);

        Optional<User> user = userRepo.findUser(username);
        if (user.isPresent()){
            User user1 = user.get();
            DTOFindUser DTOfindUser = DTOFindUser.builder()
                    .username(user1.getUsername())
                    .email(user1.getEmail())
                    .role(user1.getRole().toString())
                    .build();
            logger.info("User found: {}", username);

            return DTOfindUser;
        }else {
            logger.warn("User not found for username: {}", username);

            throw new NullPointerException("User not found for username: " + username);
        }
    }

    @Override
    public Boolean deleteUser(String id) {
        logger.info("Deleting user with ID: {}", id);

        User user = userRepo.findById(id).get();
        if (user.equals(null) || user == null){
            throw new NullPointerException("User tidak di temukan");
        }else {
            userRepo.deleteById(user.getId());
            logger.info("User deleted: {}", user.getUsername());

            return true;
        }
    }
}

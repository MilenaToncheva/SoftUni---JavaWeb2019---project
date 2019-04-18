package com.org.pizza.service;

import com.org.pizza.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel findByUsername(String username);

    void setUserRole(String id, String role);

    void deleteUser(String id);

    void editUserProfile(UserServiceModel userServiceModel, String oldPassword);
}

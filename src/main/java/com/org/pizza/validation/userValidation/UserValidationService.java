package com.org.pizza.validation.userValidation;

import com.org.pizza.domain.entities.User;
import com.org.pizza.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserValidationService {

    boolean isValid(User user);

    boolean isValid(UserServiceModel userServiceModel);

    boolean isValid(List<User> users);
}

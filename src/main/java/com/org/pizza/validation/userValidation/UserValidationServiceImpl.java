package com.org.pizza.validation.userValidation;

import com.org.pizza.domain.entities.User;
import com.org.pizza.domain.models.service.UserServiceModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(User user) {
        return user == null
                || user.getAuthorities() == null
                || user.getEmail() == null
                || user.getUsername() == null
                || user.getFirstName() == null
                || user.getLastName() == null
                || user.getPassword() == null
                || user.getPhoneNumber() == null;
    }

    @Override
    public boolean isValid(UserServiceModel userServiceModel) {
        return userServiceModel == null
                || userServiceModel.getAuthorities() == null
                || userServiceModel.getEmail() == null
                || userServiceModel.getUsername() == null
                || userServiceModel.getFirstName() == null
                || userServiceModel.getLastName() == null
                || userServiceModel.getPassword() == null
                || userServiceModel.getPhoneNumber() == null;
    }

    @Override
    public boolean isValid(List<User> users) {
        return users == null;
    }
}

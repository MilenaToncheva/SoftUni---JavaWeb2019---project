package com.org.pizza.domain.models.binding;

import com.org.pizza.constant.userMessages.UserRegistrationViolationMassages;
import org.hibernate.validator.constraints.Length;


public class UserEditBindingModel {

    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;
    private String phoneNumber;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Length(min = 4, max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_PASSWORD_LENGTH)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Length(min = 4, max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_PASSWORD_LENGTH)
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    @Length(min = 10, max = 10, message = UserRegistrationViolationMassages.USER_INCORRECT_PHONE_LENGTH)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

package com.org.pizza.domain.models.binding;

import com.org.pizza.constant.userMessages.UserRegistrationViolationMassages;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String phoneNumber;


    @NotNull
    @NotEmpty
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_FIRST_NAME_LENGTH)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @NotEmpty
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_LAST_NAME_LENGTH)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @NotEmpty
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_USERNAME_LENGTH)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @NotEmpty
    @Length(min = 4, max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_PASSWORD_LENGTH)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @NotEmpty
    @Length(min = 4, max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_PASSWORD_LENGTH)
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull
    @NotEmpty
    @Length(max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_EMAIL_LENGTH)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @NotEmpty
    @Length(min = 10, max = 10, message = UserRegistrationViolationMassages.USER_INCORRECT_PHONE_LENGTH)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

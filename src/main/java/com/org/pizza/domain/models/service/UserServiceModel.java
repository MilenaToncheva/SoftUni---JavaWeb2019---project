package com.org.pizza.domain.models.service;

import com.org.pizza.constant.userMessages.UserRegistrationViolationMassages;
import com.org.pizza.domain.entities.Role;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Set<RoleServiceModel> authorities;


    @NotEmpty
    @NotNull
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_FIRST_NAME_LENGTH)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty
    @NotNull
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_LAST_NAME_LENGTH)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty
    @NotNull
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_USERNAME_LENGTH)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    @NotNull
    @Length(min = 4, max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_PASSWORD_LENGTH)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    @NotNull
    @Length(min = 8, max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_EMAIL_LENGTH)
    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty
    @NotNull
    @Length(min = 5, max = 13, message = UserRegistrationViolationMassages.USER_INCORRECT_PHONE_LENGTH)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<RoleServiceModel> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities;
    }
}

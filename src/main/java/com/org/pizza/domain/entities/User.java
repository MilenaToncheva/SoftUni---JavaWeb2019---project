package com.org.pizza.domain.entities;

import com.org.pizza.constant.userMessages.UserRegistrationViolationMassages;
import com.org.pizza.domain.entities.pizza.Role;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    private String firstName;
    private String LastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Set<Role> authorities;


    @NotEmpty
    @Column(name = "first_name", nullable = false, updatable = false, columnDefinition = "VARCHAR (16)")
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_FIRST_NAME_LENGTH)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty
    @Column(name = "last_name", nullable = false, updatable = false, columnDefinition = "VARCHAR (16)")
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_LAST_NAME_LENGTH)
    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @NotEmpty
    @Column(name = "username", nullable = false, updatable = false, columnDefinition = "VARCHAR (16)")
    @Length(min = 4, max = 16, message = UserRegistrationViolationMassages.USER_INCORRECT_USERNAME_LENGTH)
    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @Column(name = "password", nullable = false, updatable = false, columnDefinition = "VARCHAR (60)")
    @Length(min = 8, max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_PASSWORD_LENGTH)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    @Column(name = "email", nullable = false, updatable = false, columnDefinition = "VARCHAR (60)")
    @Length(max = 60, message = UserRegistrationViolationMassages.USER_INCORRECT_EMAIL_LENGTH)
    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR (10)")
    @Length(min = 10, max = 10, message = UserRegistrationViolationMassages.USER_INCORRECT_PHONE_LENGTH)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}

package com.org.pizza.domain.models.view;

import com.org.pizza.domain.models.service.RoleServiceModel;
import com.org.pizza.domain.models.service.UserServiceModel;
import com.org.pizza.util.customMappings.CustomMapping;
import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.stream.Collectors;

public class UserAllViewModel {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private Set<String> authorities;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<String> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toSet());
    }

}

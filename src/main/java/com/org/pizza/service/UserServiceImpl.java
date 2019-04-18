package com.org.pizza.service;

import com.org.pizza.domain.entities.User;
import com.org.pizza.domain.models.service.RoleServiceModel;
import com.org.pizza.domain.models.service.UserServiceModel;
import com.org.pizza.error.WrongPassword;
import com.org.pizza.error.WrongUsernameOrPassword;
import com.org.pizza.repository.UserRepository;
import com.org.pizza.validation.errors.UserProfileEditException;
import com.org.pizza.validation.errors.UserRegistrationException;
import com.org.pizza.validation.userValidation.UserValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.org.pizza.constant.UserAuthoritiesConstants.*;
import static com.org.pizza.constant.commonMessages.CommonMessages.INVALID_DATA_INPUT;
import static com.org.pizza.constant.errorMessages.user.UserErrorMessages.*;
import static com.org.pizza.constant.userMessages.UserLoginMessages.WRONG_PASSWORD;
import static com.org.pizza.constant.userMessages.UserLoginMessages.WRONG_USERNAME_OR_PASSWORD;

@Service
public class UserServiceImpl implements UserService {

    private final static String USER_NOT_FOUND = "User not found!";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserValidationService userValidationService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, UserValidationService userValidationService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userValidationService = userValidationService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        this.roleService.seedRolesInDb();

        if (!userValidationService.isValid(userServiceModel)) {
            throw new UserRegistrationException(INVALID_DATA_INPUT);
        }

        checkIfUserDataAlreadyExist(userServiceModel);

        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            RoleServiceModel role = this.roleService.findByAuthority(ROLE_USER);
            userServiceModel.getAuthorities().add(role);
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        user = this.userRepository.save(user);

        return this.modelMapper.map(user, UserServiceModel.class);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new WrongUsernameOrPassword(WRONG_USERNAME_OR_PASSWORD));
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserServiceModel> userServiceModels = users
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());

        return userServiceModels;
    }

    @Override
    public void deleteUser(String id) {
        User userToDelete = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));

        this.userRepository.delete(userToDelete);
    }

    @Override
    public void setUserRole(String id, String role) {
        User userToEdit = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));

        UserServiceModel userServiceModel = this.modelMapper.map(userToEdit, UserServiceModel.class);
        userServiceModel.getAuthorities().clear();

        switch (role) {
            case "user":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority(ROLE_USER));
                break;
            case "moderator":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority(ROLE_USER));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority(ROLE_MODERATOR));
                break;
            case "admin":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority(ROLE_USER));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority(ROLE_MODERATOR));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority(ROLE_ADMIN));
                break;
        }
        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        return userServiceModel;
    }

    @Override
    public void editUserProfile(UserServiceModel userServiceModel, String oldPassword) {

        if (!userValidationService.isValid(userServiceModel)) {
            throw new UserRegistrationException(INVALID_DATA_INPUT);
        }
        User user = this.userRepository.findByUsername(userServiceModel.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));

        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new WrongPassword(WRONG_PASSWORD);
        }

        if (!"".equals(userServiceModel.getPassword())) {
            user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        }

        if (!userServiceModel.getPhoneNumber().equals("") &&
                !user.getPhoneNumber().equals(userServiceModel.getPhoneNumber())) {

            this.checkIfUserDataAlreadyExist(userServiceModel.getPhoneNumber());
            user.setPhoneNumber(userServiceModel.getPhoneNumber());
        }
        this.userRepository.save(user);
    }

    private void checkIfUserDataAlreadyExist(UserServiceModel userServiceModel) {
        List<User> users = this.userRepository
                .findAllByPhoneNumberOrEmailOrUsername(
                        userServiceModel.getPhoneNumber(),
                        userServiceModel.getEmail(),
                        userServiceModel.getUsername()
                ).orElse(null);

        if (!userValidationService.isValid(users)) {
            throw new UserRegistrationException(USER_ALREADY_EXIST);
        }
    }

    private void checkIfUserDataAlreadyExist(String phone) {
        List<User> users = this.userRepository
                .findAllByPhoneNumber(phone).orElse(null);

        if (!userValidationService.isValid(users)) {
            throw new UserProfileEditException(USER_PHONE_NUMBER_ALREADY_EXIST);
        }
    }
}

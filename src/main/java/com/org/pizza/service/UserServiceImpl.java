package com.org.pizza.service;

import com.org.pizza.domain.entities.User;
import com.org.pizza.domain.models.service.RoleServiceModel;
import com.org.pizza.domain.models.service.UserServiceModel;
import com.org.pizza.repository.UserRepository;
import com.org.pizza.validation.errors.UserRegistrationException;
import com.org.pizza.validation.userValidation.UserValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

import static com.org.pizza.constant.UserAuthoritiesConstats.ROLE_USER;
import static com.org.pizza.constant.errorMessages.user.UserErrorMessages.INVALID_DATA_INPUT;
import static com.org.pizza.constant.errorMessages.user.UserErrorMessages.USER_ALREADY_EXIST;

@Service
public class UserServiceImpl implements UserService {

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

        List<User> users = this.userRepository
                .findAllByPhoneNumberOrEmailOrUsername(
                        userServiceModel.getPhoneNumber(),
                        userServiceModel.getEmail(),
                        userServiceModel.getUsername()
                ).orElse(null);

        if (!userValidationService.isValid(users)) {
            throw new UserRegistrationException(USER_ALREADY_EXIST);
        }

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
}

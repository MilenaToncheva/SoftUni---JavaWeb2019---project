package com.org.pizza.service;

import com.org.pizza.constant.UserAuthoritiesConstants;
import com.org.pizza.domain.entities.Role;
import com.org.pizza.domain.models.service.RoleServiceModel;
import com.org.pizza.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.save(new Role(UserAuthoritiesConstants.ROLE_USER));
            this.roleRepository.save(new Role(UserAuthoritiesConstants.ROLE_MODERATOR));
            this.roleRepository.save(new Role(UserAuthoritiesConstants.ROLE_ADMIN));
            this.roleRepository.save(new Role(UserAuthoritiesConstants.ROLE_ROOT));
        }
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        List<Role> roles = this.roleRepository.findAll();
        Set<RoleServiceModel> roleServiceModels = roles
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
        return roleServiceModels;
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        Role role = this.roleRepository.findByAuthority(authority);
        RoleServiceModel roleServiceModel = this.modelMapper.map(role, RoleServiceModel.class);

        return roleServiceModel;
    }
}

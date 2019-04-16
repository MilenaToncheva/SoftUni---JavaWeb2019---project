package com.org.pizza.repository;

import com.org.pizza.domain.entities.Role;
import org.hibernate.loader.custom.CustomQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByAuthority(String authority);
}

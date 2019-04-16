package com.org.pizza.repository;

import com.org.pizza.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<List<User>> findAllByPhoneNumberOrEmailOrUsername(String phoneNumber, String email, String username);

    Optional<User> findByUsername(String username);
}

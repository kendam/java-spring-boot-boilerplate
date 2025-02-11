package com.ksolutions.javaspringbootboilerplate.repository;

import com.ksolutions.javaspringbootboilerplate.entity.Role;
import com.ksolutions.javaspringbootboilerplate.util.Constants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(Constants.RoleEnum name);
}

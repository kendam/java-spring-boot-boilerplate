package com.ksolutions.javaspringbootboilerplate.repository;

import com.ksolutions.javaspringbootboilerplate.entity.LocationState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateRepository extends JpaRepository<LocationState, String> {
    Optional<LocationState> findByStateName(String stateName);
}

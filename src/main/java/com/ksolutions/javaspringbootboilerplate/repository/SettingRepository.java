package com.ksolutions.javaspringbootboilerplate.repository;

import com.ksolutions.javaspringbootboilerplate.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettingRepository extends JpaRepository<Setting, UUID> {
}

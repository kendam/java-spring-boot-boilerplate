package com.ksolutions.javaspringbootboilerplate.service;

import com.ksolutions.javaspringbootboilerplate.entity.Setting;
import com.ksolutions.javaspringbootboilerplate.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final SettingRepository settingRepository;

    public Setting create() {
        return settingRepository.save(Setting.builder().key("foo").value("bar").build());
    }
}

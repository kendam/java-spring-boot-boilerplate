package com.mewebstudio.javaspringbootboilerplate.service;

import com.mewebstudio.javaspringbootboilerplate.entity.City;
import com.mewebstudio.javaspringbootboilerplate.exception.NotFoundException;
import com.mewebstudio.javaspringbootboilerplate.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    private final MessageSourceService messageSourceService;

    /**
     * Count cities.
     *
     * @return Long
     */
    public long count() {
        return cityRepository.count();
    }

    /**
     * Find by city name.
     *
     * @param cityName String
     * @return City
     */
    public City findByCityName(String cityName) {
        return cityRepository.findByCityName(cityName)
                .orElseThrow(() -> new NotFoundException(messageSourceService.get("city_not_found")));
    }

    /**
     * Create city
     *
     * @param city City
     * @return City
     */
    public City create(final City city) {
        return cityRepository.save(city);
    }

    /**
     * Save cities
     *
     * @param cityList List
     * @return List
     */
    public List<City> saveList(List<City> cityList) {
        return cityRepository.saveAll(cityList);
    }

    public Optional<City> getCityById(int cityId) {
        return cityRepository.findById(String.valueOf(cityId));
    }
}

package com.mewebstudio.javaspringbootboilerplate.service;

import com.mewebstudio.javaspringbootboilerplate.dto.request.user.CreateUserRequest;
import com.mewebstudio.javaspringbootboilerplate.entity.City;
import com.mewebstudio.javaspringbootboilerplate.entity.LocationState;
import com.mewebstudio.javaspringbootboilerplate.entity.Role;
import com.mewebstudio.javaspringbootboilerplate.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DummyDataService implements CommandLineRunner {
    private final RoleService roleService;

    private final UserService userService;

    private final StateService stateService;

    private final CityService cityService;

    @Override
    public void run(String... args) throws Exception {
        if (roleService.count() == 0) {
            log.info("Creating roles...");
            createRoles();
            log.info("Roles created.");
        }

        if (stateService.count() == 0) {
            log.info("Creating states...");
            createStates();
            log.info("states created.");
        }

        if (cityService.count() == 0) {
            log.info("Creating cities...");
            createCity();
            log.info("city created.");
        }

        if (userService.count() == 0) {
            log.info("Creating users...");
            createUsers();
            log.info("Users created.");
        }
    }

    /**
     * Create roles.
     */
    private void createRoles() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.builder().name(Constants.RoleEnum.ADMIN).build());
        roleList.add(Role.builder().name(Constants.RoleEnum.USER).build());

        roleService.saveList(roleList);
    }

    /**
     * Create states
     */
    private void createStates() {
        List<LocationState> locationStateList = new ArrayList<>();
        locationStateList.add(LocationState.builder().stateName("Abia").build());
        locationStateList.add(LocationState.builder().stateName("Adamawa").build());
        locationStateList.add(LocationState.builder().stateName("Akwa Ibom").build());
        locationStateList.add(LocationState.builder().stateName("Anambra").build());
        locationStateList.add(LocationState.builder().stateName("Bauchi").build());
        locationStateList.add(LocationState.builder().stateName("Bayelsa").build());
        locationStateList.add(LocationState.builder().stateName("Benue").build());
        locationStateList.add(LocationState.builder().stateName("Borno").build());
        locationStateList.add(LocationState.builder().stateName("Cross River").build());
        locationStateList.add(LocationState.builder().stateName("Delta").build());
        locationStateList.add(LocationState.builder().stateName("Ebonyi").build());
        locationStateList.add(LocationState.builder().stateName("Edo").build());
        locationStateList.add(LocationState.builder().stateName("Ekiti").build());
        locationStateList.add(LocationState.builder().stateName("Enugu").build());
        locationStateList.add(LocationState.builder().stateName("Gombe").build());
        locationStateList.add(LocationState.builder().stateName("Imo").build());
        locationStateList.add(LocationState.builder().stateName("Jigawa").build());
        locationStateList.add(LocationState.builder().stateName("Kaduna").build());
        locationStateList.add(LocationState.builder().stateName("Kano").build());
        locationStateList.add(LocationState.builder().stateName("Katsina").build());
        locationStateList.add(LocationState.builder().stateName("Kebbi").build());
        locationStateList.add(LocationState.builder().stateName("Kogi").build());
        locationStateList.add(LocationState.builder().stateName("Kwara").build());
        locationStateList.add(LocationState.builder().stateName("Lagos").build());
        locationStateList.add(LocationState.builder().stateName("Nasarawa").build());
        locationStateList.add(LocationState.builder().stateName("Niger").build());
        locationStateList.add(LocationState.builder().stateName("Ogun").build());
        locationStateList.add(LocationState.builder().stateName("Ondo").build());
        locationStateList.add(LocationState.builder().stateName("Osun").build());
        locationStateList.add(LocationState.builder().stateName("Oyo").build());
        locationStateList.add(LocationState.builder().stateName("Plateau").build());
        locationStateList.add(LocationState.builder().stateName("Rivers").build());
        locationStateList.add(LocationState.builder().stateName("Sokoto").build());
        locationStateList.add(LocationState.builder().stateName("Taraba").build());
        locationStateList.add(LocationState.builder().stateName("Yobe").build());
        locationStateList.add(LocationState.builder().stateName("Zamfara").build());

        stateService.saveList(locationStateList);
    }

    private LocationState getState(String stateName) {
        return stateService.findByStateName(stateName);
    }

    private City getCity(String cityName) {
        return cityService.findByCityName(cityName);

    }


    private void createCity() {


        List<City> cityList = new ArrayList<>();
        cityList.add(City.builder()
                .cityName("Ikeja")
                .state(getState("Lagos"))
                .build());


        cityService.saveList(cityList);
    }


    /**
     * Create users.
     *
     * @throws BindException Bind exception
     */
    private void createUsers() throws BindException {
        List<String> roleList = new ArrayList<>();
        roleList.add(Constants.RoleEnum.ADMIN.getValue());
        roleList.add(Constants.RoleEnum.USER.getValue());
        String defaultPassword = "P@sswd123.";

        userService.create(CreateUserRequest.builder()
                .email("admin@example.com")
                .password(defaultPassword)
                .userName("admin")
                .firstName("John")
                .lastName("DOE")
                .city(getCity("Ikeja"))
                .state(getState("Lagos"))
                .gender("Male")
                .dateOfBirth("01/01/1970")
                .roles(roleList)
                .isEmailVerified(true)
                .isBlocked(false)
                .build());

        userService.create(CreateUserRequest.builder()
                .email("user@example.com")
                .password(defaultPassword)
                .userName("user")
                .city(getCity("Ikeja"))
                .state(getState("Lagos"))
                .firstName("John")
                .gender("Male")
                .dateOfBirth("01/01/1970")
                .lastName("DOE")
                .roles(List.of(roleList.get(1)))
                .isEmailVerified(true)
                .isBlocked(false)
                .build());
    }
}

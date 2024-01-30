package com.mewebstudio.javaspringbootboilerplate.service;

import com.mewebstudio.javaspringbootboilerplate.dto.request.user.CreateUserRequest;
import com.mewebstudio.javaspringbootboilerplate.entity.City;
import com.mewebstudio.javaspringbootboilerplate.entity.Role;
import com.mewebstudio.javaspringbootboilerplate.entity.State;
import com.mewebstudio.javaspringbootboilerplate.exception.NotFoundException;
import com.mewebstudio.javaspringbootboilerplate.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<State> stateList = new ArrayList<>();
        stateList.add(State.builder().stateName("Abia").build());
        stateList.add(State.builder().stateName("Adamawa").build());
        stateList.add(State.builder().stateName("Akwa Ibom").build());
        stateList.add(State.builder().stateName("Anambra").build());
        stateList.add(State.builder().stateName("Bauchi").build());
        stateList.add(State.builder().stateName("Bayelsa").build());
        stateList.add(State.builder().stateName("Benue").build());
        stateList.add(State.builder().stateName("Borno").build());
        stateList.add(State.builder().stateName("Cross River").build());
        stateList.add(State.builder().stateName("Delta").build());
        stateList.add(State.builder().stateName("Ebonyi").build());
        stateList.add(State.builder().stateName("Edo").build());
        stateList.add(State.builder().stateName("Ekiti").build());
        stateList.add(State.builder().stateName("Enugu").build());
        stateList.add(State.builder().stateName("Gombe").build());
        stateList.add(State.builder().stateName("Imo").build());
        stateList.add(State.builder().stateName("Jigawa").build());
        stateList.add(State.builder().stateName("Kaduna").build());
        stateList.add(State.builder().stateName("Kano").build());
        stateList.add(State.builder().stateName("Katsina").build());
        stateList.add(State.builder().stateName("Kebbi").build());
        stateList.add(State.builder().stateName("Kogi").build());
        stateList.add(State.builder().stateName("Kwara").build());
        stateList.add(State.builder().stateName("Lagos").build());
        stateList.add(State.builder().stateName("Nasarawa").build());
        stateList.add(State.builder().stateName("Niger").build());
        stateList.add(State.builder().stateName("Ogun").build());
        stateList.add(State.builder().stateName("Ondo").build());
        stateList.add(State.builder().stateName("Osun").build());
        stateList.add(State.builder().stateName("Oyo").build());
        stateList.add(State.builder().stateName("Plateau").build());
        stateList.add(State.builder().stateName("Rivers").build());
        stateList.add(State.builder().stateName("Sokoto").build());
        stateList.add(State.builder().stateName("Taraba").build());
        stateList.add(State.builder().stateName("Yobe").build());
        stateList.add(State.builder().stateName("Zamfara").build());

        stateService.saveList(stateList);
    }

    private Optional<Optional<State>> getState(int stateId){
       return Optional.ofNullable(stateService.getStateById(stateId));


    }

    private State toState(State state) {

        return state;
    }

    private Optional<City> getCity(int cityId){
        return Optional.ofNullable(cityService.getCityById(cityId)
                .orElseThrow(() -> new NotFoundException("city cannot be found")));

    }

    private void createCity() {


        List<City> cityList = new ArrayList<>();
        cityList.add(City.builder()
                .cityName("Ikeja")
                        .state(getState(25))
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
                        .city(getCity(1))
                        .state(getState(25))
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
                .city(getCity(1))
                .state(getState(25))
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

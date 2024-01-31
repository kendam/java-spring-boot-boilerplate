package com.mewebstudio.javaspringbootboilerplate.service;

import com.mewebstudio.javaspringbootboilerplate.entity.LocationState;
import com.mewebstudio.javaspringbootboilerplate.exception.NotFoundException;
import com.mewebstudio.javaspringbootboilerplate.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StateService {
    private final StateRepository stateRepository;

    private final MessageSourceService messageSourceService;

    /**
     * Count states.
     *
     * @return Long
     */
    public long count() {
        return stateRepository.count();
    }

    /**
     * Find by state name.
     *
     * @param stateName String
     * @return State
     */
    public LocationState findByStateName(String stateName) {
        return stateRepository.findByStateName(stateName)
            .orElseThrow(() -> new NotFoundException(messageSourceService.get("state_not_found")));
    }

    /**
     * Create state
     *
     * @param locationState State
     * @return Role
     */
    public LocationState create(final LocationState locationState) {
        return stateRepository.save(locationState);
    }

    /**
     * Save list states.
     *
     * @param locationStateList List
     * @return List
     */
    public List<LocationState> saveList(List<LocationState> locationStateList) {
        return stateRepository.saveAll(locationStateList);
    }

    /**
     * Get state by Id
     *
     * @param stateId int
     * @return State
     */

    public Optional<LocationState> getStateById(int stateId){
        return stateRepository.findById(String.valueOf(stateId));
    }
}

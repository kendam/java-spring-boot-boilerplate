package com.mewebstudio.javaspringbootboilerplate.service;

import com.mewebstudio.javaspringbootboilerplate.entity.State;
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
    public State findByStateName(String stateName) {
        return stateRepository.findByStateName(stateName)
            .orElseThrow(() -> new NotFoundException(messageSourceService.get("state_not_found")));
    }

    /**
     * Create state
     *
     * @param state State
     * @return Role
     */
    public State create(final State state) {
        return stateRepository.save(state);
    }

    /**
     * Save list states.
     *
     * @param stateList List
     * @return List
     */
    public List<State> saveList(List<State> stateList) {
        return stateRepository.saveAll(stateList);
    }

    /**
     * Get state by Id
     *
     * @param stateId int
     * @return State
     */

    public Optional<State> getStateById(int stateId){
        return stateRepository.findById(String.valueOf(stateId));
    }
}

package com.gugafood.gugafood.domain.service;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.State;
import com.gugafood.gugafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateRegisterService {

    @Autowired
    private StateRepository stateRepository;

    public State add(State state) {
        return stateRepository.add(state);
    }

    public State update(State state) {

        State state1 = stateRepository.findById(state.getId());

        if (state1 == null) {
            throw new EntityNotFoundException(
                    String.format("State with id %d does not exist!", state.getId())
            );
        }
        return stateRepository.update(state);
    }

    public void delete(Long id) {
        try {
            stateRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("State with id %d does not exist!", id)
            );


        } catch (DataIntegrityViolationException e) {

            throw new EntityInUseException(
                    String.format("State with id %d cannot be removed because it's in use", id)
            );
        }
    }
}

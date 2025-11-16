package com.gugafood.gugafood.domain.service;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.State;
import com.gugafood.gugafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateRegisterService {

    @Autowired
    private StateRepository stateRepository;

    public State add(State state) {
        return stateRepository.save(state);
    }

    public State update(State state) {

       Optional<State> newState = stateRepository.findById(state.getId());

        if (newState.isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("State with id %d does not exist!", state.getId())
            );
        }
        return stateRepository.save(newState.get());
    }

    public void delete(Long id) {
      if(!stateRepository.existsById(id)){
          throw new EntityNotFoundException(
                  String.format("Kitchen with id %d cannot be removed because it does not exist",id)
          );
      }

      try{
          stateRepository.deleteById(id);
          stateRepository.flush();
      }catch (DataIntegrityViolationException e){
          throw new EntityInUseException(
                  String.format("Kitchen with id %d cannot be removed because it's in use",id)
          );
      }
    }
}

package com.gugafood.gugafood.domain.service;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenRegisterService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen add(Kitchen kitchen) {
        return kitchenRepository.add(kitchen);
    }

    public Kitchen update(Kitchen kitchen) {
        return kitchenRepository.update(kitchen);
    }

    public void delete(Long id){
        try {
            kitchenRepository.delete(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("Kitchen with id %d does not exist!",id)
            );
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Kitchen with id %d cannot be removed because it's in use",id)
            );
        }

    }
}

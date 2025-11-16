package com.gugafood.gugafood.domain.service;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenRegisterService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen add(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public Kitchen update(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void delete(Long id){
        if (!kitchenRepository.existsById(id)){
            throw new EntityNotFoundException(
                    String.format("Kitchen with id %d cannot be removed because it does not exist",id)
            );
        }

        try {
            kitchenRepository.deleteById(id);
            kitchenRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Kitchen with id %d cannot be removed because it's in use",id)
            );
        }
    }
}

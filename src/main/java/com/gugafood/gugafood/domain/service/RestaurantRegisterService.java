package com.gugafood.gugafood.domain.service;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant add(Restaurant restaurant) {
        return restaurantRepository.add(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.update(restaurant);
    }

    public void delete(Long id){
        try {
            restaurantRepository.delete(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("Restaurant with id %d does not exist!",id)
            );
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurant with id %d cannot be removed because it's in use",id)
            );
        }

    }
}

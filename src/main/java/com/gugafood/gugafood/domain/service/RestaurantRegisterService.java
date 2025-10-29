package com.gugafood.gugafood.domain.service;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant add(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId);

        if (kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Kitchen with id %d does not exist", kitchenId)
            );
        }

        restaurant.setKitchen(kitchen);

        return restaurantRepository.add(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant restaurant1 = restaurantRepository.findById(restaurant.getId());

        if (restaurant1 == null) {
            throw new EntityNotFoundException(
                    String.format("Restaurant with id %d does not exist", restaurant.getId())
            );
        } else {

            BeanUtils.copyProperties(restaurant, restaurant1, "id");
            return restaurantRepository.update(restaurant1);
        }
    }

    public void delete(Long id) {
        try {
            restaurantRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Restaurant with id %d does not exist!", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurant with id %d cannot be removed because it's in use", id)
            );
        }

    }
}

package com.gugafood.gugafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import com.gugafood.gugafood.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
       Optional<Restaurant>  restaurant = restaurantRepository.findById(id);

        if (restaurant.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.ok(restaurant.get());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {

        try {
            restaurant = restaurantRegisterService.add(restaurant);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(restaurant.getId())
                    .toUri();

            return ResponseEntity.created(location).body(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Optional<Restaurant> newRestaurant = restaurantRepository.findById(id);

        if (newRestaurant.isPresent()){
            BeanUtils.copyProperties(restaurant,newRestaurant.get(),"id");

            Restaurant updatedRestaurant = restaurantRegisterService.update(newRestaurant.get());
            return ResponseEntity.ok(updatedRestaurant);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Restaurant> delete(@PathVariable Long id) {

        try {
            restaurantRegisterService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String,Object> fields){

        Optional<Restaurant> targetRestaurant = restaurantRepository.findById(id);

        if (targetRestaurant.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        merge(fields,targetRestaurant.get());

        return update(id,targetRestaurant.get());
    }

    private void merge(Map<String, Object> sourceFields, Restaurant targetRestaurant) {

        ObjectMapper objMapper = new ObjectMapper();
        Restaurant sourceRestaurant = objMapper.convertValue(sourceFields,Restaurant.class);

        sourceFields.forEach((propertyName, propertyValue) ->{

            Field field = ReflectionUtils.findField(Restaurant.class,propertyName);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field,sourceRestaurant);

            System.out.println(propertyName + " = " + propertyValue + " = " + newValue);

            ReflectionUtils.setField(field,targetRestaurant,newValue);

            System.out.println(propertyName + " = " + propertyValue);
        });
    }
}

package com.gugafood.gugafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
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

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

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

        try {
            Restaurant restaurant1 = restaurantRepository.findById(id);


            if (restaurant1 != null) {

                BeanUtils.copyProperties(restaurant, restaurant1, "id");

                restaurant1 = restaurantRegisterService.update(restaurant1);

                return ResponseEntity.ok(restaurant1);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

        Restaurant targetRestaurant = restaurantRepository.findById(id);

        if (targetRestaurant == null){
            return ResponseEntity.notFound().build();
        }

        merge(fields,targetRestaurant);

        return update(id,targetRestaurant);
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

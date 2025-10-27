package com.gugafood.gugafood.api.controller;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import com.gugafood.gugafood.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    
//    @GetMapping("/{id}")
//    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
//        Restaurant restaurant = restaurantRepository.findById(id);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://api.gugafood.local:8080/restaurants");
//
//      return ResponseEntity.status(HttpStatus.OK).body(restaurant);
//      return ResponseEntity.ok(restaurant);
//      return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .headers(headers)
//                .build();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Restaurant add(@RequestBody Restaurant restaurant) {
//        return restaurantRepository.add(restaurant);
//    }

    @PostMapping
    public ResponseEntity<Restaurant> add(@RequestBody Restaurant restaurant) {

      restaurant = restaurantRegisterService.add(restaurant);

      URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(restaurant.getId())
                .toUri();

        return ResponseEntity.created(location).body(restaurant);
    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id,@RequestBody Restaurant restaurant) {

       Restaurant restaurant1 = restaurantRepository.findById(id);



        if (restaurant1 != null) {

    //      restaurant1.setName(restaurant.getName());
            BeanUtils.copyProperties(restaurant,restaurant1,"id");

            restaurantRegisterService.update(restaurant1);

            return ResponseEntity.ok(restaurant1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Restaurant> delete(@PathVariable Long id) {

        try {
          restaurantRegisterService.delete(id);

          return ResponseEntity.noContent().build();
        }catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

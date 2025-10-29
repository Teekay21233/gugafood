package com.gugafood.gugafood.api.controller;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.City;
import com.gugafood.gugafood.domain.repository.CityRepository;
import com.gugafood.gugafood.domain.service.CityRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegisterService;

    @GetMapping
    public List<City> list() {
        return cityRepository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {

        City city = cityRepository.findById(id);

        if (city != null) {
            return ResponseEntity.ok(city);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<City> add(@RequestBody City city) {
        city = cityRegisterService.add(city);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();

        return ResponseEntity.created(location).body(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City city) {

        try {
            City city1 = cityRepository.findById(id);

            if (city == null){
                throw new EntityNotFoundException(
                        String.format("City with id %d does not exist",id)
                );
            }

            BeanUtils.copyProperties(city,city1,"id");

            return ResponseEntity.ok(city1);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            cityRegisterService.delete(id);

            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

//    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Respo)
}

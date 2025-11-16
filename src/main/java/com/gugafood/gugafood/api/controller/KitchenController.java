package com.gugafood.gugafood.api.controller;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import com.gugafood.gugafood.domain.service.KitchenRegisterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenRegisterService kitchenRegisterService;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
//        Kitchen kitchen = kitchenRepository.findById(id);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://api.gugafood.local:8080/kitchens");
//
//      return ResponseEntity.status(HttpStatus.OK).body(kitchen);
//      return ResponseEntity.ok(kitchen);
//      return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .headers(headers)
//                .build();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
       Optional<Kitchen> kitchen = kitchenRepository.findById(id);

        if (kitchen.isPresent()) {
            return ResponseEntity.ok(kitchen.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Kitchen add(@RequestBody Kitchen kitchen) {
//        return kitchenRepository.add(kitchen);
//    }

    @PostMapping
    public ResponseEntity<Kitchen> add(@RequestBody Kitchen kitchen) {


      kitchen = kitchenRegisterService.add(kitchen);

      URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(kitchen.getId())
                .toUri();

        return ResponseEntity.created(location).body(kitchen);
    }

    @PutMapping("{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id,@RequestBody Kitchen kitchen) {
           Optional<Kitchen> newKitchen = kitchenRepository.findById(id);

           if (newKitchen.isPresent()){
               BeanUtils.copyProperties(kitchen,newKitchen.get(),"id");

              Kitchen updatedKitchen = kitchenRegisterService.update(newKitchen.get());
              return ResponseEntity.ok(updatedKitchen);
           }

            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Kitchen> delete(@PathVariable Long id) {

        try {
          kitchenRegisterService.delete(id);

          return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

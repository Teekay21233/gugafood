    package com.gugafood.gugafood.api.controller;

    import com.gugafood.gugafood.domain.exception.EntityInUseException;
    import com.gugafood.gugafood.domain.model.State;
    import com.gugafood.gugafood.domain.repository.StateRepository;
    import com.gugafood.gugafood.domain.service.StateRegisterService;
    import org.springframework.beans.BeanUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import com.gugafood.gugafood.domain.exception.EntityNotFoundException;

    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

    import java.net.URI;
    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/states")
    public class StateController {

        @Autowired
        private StateRepository stateRepository;

        @Autowired
        private StateRegisterService stateRegisterService;

        @GetMapping
        public List<State> list() {
            return stateRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<State> findById(@PathVariable Long id) {
            Optional<State> state = stateRepository.findById(id);

            if (state.isPresent()){
                return ResponseEntity.ok(state.get());
            }
            return ResponseEntity.notFound().build();
        }

        @PostMapping
        public ResponseEntity<?> add(@RequestBody State state){
            state = stateRegisterService.add(state);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(state.getId())
                    .toUri();

            return ResponseEntity.created(location).body(state);
        }

        @PutMapping("{id}")
        public ResponseEntity<?> update(@PathVariable Long id, @RequestBody State state){

               Optional<State> newState = stateRepository.findById(id);

                if (newState.isPresent()){

                    BeanUtils.copyProperties(state,newState.get(),"id");

                    stateRegisterService.update(newState.get());

                    return ResponseEntity.ok(newState.get());
                }else {
                    return ResponseEntity.notFound().build();
                }
        }

        @DeleteMapping("{id}")
        public ResponseEntity<State> delete(@PathVariable Long id){
            try {
                stateRegisterService.delete(id);

                return ResponseEntity.noContent().build();
            }catch (EntityInUseException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }catch (EntityNotFoundException e){
                return ResponseEntity.notFound().build();
            }
        }
    }

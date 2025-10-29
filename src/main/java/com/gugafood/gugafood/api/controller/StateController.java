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

    @RestController
    @RequestMapping("/states")
    public class StateController {

        @Autowired
        private StateRepository stateRepository;

        @Autowired
        private StateRegisterService stateRegisterService;

        @GetMapping
        public List<State> list() {
            return stateRepository.list();
        }
        @GetMapping("/{id}")
        public State findById(@PathVariable Long id) {
            return stateRepository.findById(id);
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
            try {
                State state1 = stateRepository.findById(id);

                if (state1 != null){

                    BeanUtils.copyProperties(state,state1,"id");

                    stateRegisterService.update(state1);

                    return ResponseEntity.ok(state1);
                }else {
                    return ResponseEntity.notFound().build();
                }
            }catch (EntityNotFoundException e){
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

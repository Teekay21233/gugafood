    package com.gugafood.gugafood.api.controller;

    import com.gugafood.gugafood.domain.model.State;
    import com.gugafood.gugafood.domain.repository.StateRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;

    @RestController
    @RequestMapping("/states")
    public class StateController {

        @Autowired
        private StateRepository stateRepository;

        @GetMapping
        public List<State> list() {
            return stateRepository.list();
        }
        @GetMapping("/{id}")
        public State findById(@PathVariable Long id) {
            return stateRepository.findById(id);
        }
    }

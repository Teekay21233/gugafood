package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.State;

import java.util.List;

public interface StateRepository {
    List<State> list();
    State findById(Long id);
    State add(State state);
    State update(State state);
    void delete(Long id);
}

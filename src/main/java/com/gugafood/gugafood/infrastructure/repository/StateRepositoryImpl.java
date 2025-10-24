package com.gugafood.gugafood.infrastructure.repository;

import com.gugafood.gugafood.domain.model.State;
import com.gugafood.gugafood.domain.repository.StateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> list(){
        TypedQuery<State> query = manager.createQuery("from State", State.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public State add(State state){
        return manager.merge(state);
    }

    @Override
    public State findById(Long id){
        return manager.find(State.class,id);
    }

    @Override
    @Transactional
    public State update(State state){
        return add(state);
    }

    @Override
    @Transactional
    public void delete(State state){
        state = findById(state.getId());
        manager.remove(state);
    }
}

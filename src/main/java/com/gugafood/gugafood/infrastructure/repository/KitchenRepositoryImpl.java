package com.gugafood.gugafood.infrastructure.repository;

import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> list(){
        TypedQuery<Kitchen> query = manager.createQuery("from Kitchen", Kitchen.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Kitchen add(Kitchen kitchen){
        return manager.merge(kitchen);
    }

    @Override
    public Kitchen findById(Long id){
        return manager.find(Kitchen.class,id);
    }

    @Override
    @Transactional
    public Kitchen update(Kitchen kitchen){
        return add(kitchen);
    }

    @Override
    @Transactional
    public void delete(Kitchen kitchen){
        kitchen = findById(kitchen.getId());
        manager.remove(kitchen);
    }
}

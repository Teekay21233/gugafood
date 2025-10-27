package com.gugafood.gugafood.infrastructure.repository;

import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> list(){
        TypedQuery<Restaurant> query = manager.createQuery("from Restaurant", Restaurant.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Restaurant add(Restaurant restaurant){
        return manager.merge(restaurant);
    }

    @Override
    public Restaurant findById(Long id){
        return manager.find(Restaurant.class,id);
    }

    @Override
    @Transactional
    public Restaurant update(Restaurant restaurant){
        return add(restaurant);
    }

    @Override
    @Transactional
    public void delete(Long id){
       Restaurant restaurant = findById(id);

        if (restaurant == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(restaurant);
    }
}

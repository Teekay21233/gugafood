package com.gugafood.gugafood.infrastructure.repository;

import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
    public void delete(Restaurant restaurant){
        restaurant = findById(restaurant.getId());
        manager.remove(restaurant);
    }
}

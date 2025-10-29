package com.gugafood.gugafood.infrastructure.repository;

import com.gugafood.gugafood.domain.model.City;
import com.gugafood.gugafood.domain.repository.CityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list(){
        TypedQuery<City> query = manager.createQuery("from City", City.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public City add(City city){
        return manager.merge(city);
    }

    @Override
    public City findById(Long id){
        return manager.find(City.class,id);
    }

    @Override
    @Transactional
    public City update(City city){
        return add(city);
    }

    @Override
    @Transactional
    public void delete(Long id){

       City city = findById(id);

       if (city == null){
           throw new EmptyResultDataAccessException(1);
       }
        manager.remove(city);
    }
}

package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.City;

import java.util.List;

public interface CityRepository {
    List<City> list();
    City findById(Long id);
    City add(City city);
    City update(City city);
    void delete(Long id);
}

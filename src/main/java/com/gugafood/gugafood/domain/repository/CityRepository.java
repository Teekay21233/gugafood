package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {

}

package com.gugafood.gugafood.domain.service;

import com.gugafood.gugafood.domain.exception.EntityInUseException;
import com.gugafood.gugafood.domain.exception.EntityNotFoundException;
import com.gugafood.gugafood.domain.model.City;
import com.gugafood.gugafood.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {

    @Autowired
    private CityRepository cityRepository;

    public City add(City city){
        return cityRepository.add(city);
    }

    public City update(City city){
        return cityRepository.update(city);
    }

    public void delete(Long id){
        try {
            cityRepository.delete(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("City with id %d does not exist!",id)
            );
        }catch (DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("City with id %d cannot be deleted because it's in use",id)
            );
        }
    }
}

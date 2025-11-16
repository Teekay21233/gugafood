package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State,Long> {

}

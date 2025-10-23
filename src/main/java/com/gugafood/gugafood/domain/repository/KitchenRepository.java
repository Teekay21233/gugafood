package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {
    List<Kitchen> list();
    Kitchen findById(Long id);
    Kitchen add(Kitchen kitchen);
    Kitchen update(Kitchen kitchen);
    void delete(Kitchen kitchen);
}

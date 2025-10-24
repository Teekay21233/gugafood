package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.PaymentMethod;
import com.gugafood.gugafood.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {
    List<Permission> list();
    Permission findById(Long id);
    Permission add(Permission permission);
    Permission update(Permission permission);
    void delete(Permission permission);
}

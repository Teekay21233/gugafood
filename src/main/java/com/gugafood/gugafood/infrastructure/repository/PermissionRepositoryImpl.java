package com.gugafood.gugafood.infrastructure.repository;

import com.gugafood.gugafood.domain.model.Permission;
import com.gugafood.gugafood.domain.repository.PermissionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permission> list(){
        TypedQuery<Permission> query = manager.createQuery("from Permission", Permission.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Permission add(Permission permission){
        return manager.merge(permission);
    }

    @Override
    public Permission findById(Long id){
        return manager.find(Permission.class,id);
    }

    @Override
    @Transactional
    public Permission update(Permission permission){
        return add(permission);
    }

    @Override
    @Transactional
    public void delete(Permission permission){
        permission = findById(permission.getId());
        manager.remove(permission);
    }
}

package com.gugafood.gugafood.infrastructure.repository;

import com.gugafood.gugafood.domain.model.PaymentMethod;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.PaymentMethodRepository;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<PaymentMethod> list(){
        TypedQuery<PaymentMethod> query = manager.createQuery("from PaymentMethod", PaymentMethod.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public PaymentMethod add(PaymentMethod paymentMethod){
        return manager.merge(paymentMethod);
    }

    @Override
    public PaymentMethod findById(Long id){
        return manager.find(PaymentMethod.class,id);
    }

    @Override
    @Transactional
    public PaymentMethod update(PaymentMethod paymentMethod){
        return add(paymentMethod);
    }

    @Override
    @Transactional
    public void delete(PaymentMethod paymentMethod){
        paymentMethod = findById(paymentMethod.getId());
        manager.remove(paymentMethod);
    }
}

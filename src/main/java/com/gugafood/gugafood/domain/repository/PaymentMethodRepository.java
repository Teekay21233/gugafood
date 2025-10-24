package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {
    List<PaymentMethod> list();
    PaymentMethod findById(Long id);
    PaymentMethod add(PaymentMethod paymentMethod);
    PaymentMethod update(PaymentMethod paymentMethod);
    void delete(PaymentMethod paymentMethod);
}

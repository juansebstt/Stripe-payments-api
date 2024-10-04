package com.stripe.payment.repositories;

import com.stripe.payment.common.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

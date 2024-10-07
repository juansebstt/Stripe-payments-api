package com.stripe.payment.repositories;

import com.stripe.payment.common.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Payment repository.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    /**
     * Find by payment intent id payment.
     *
     * @param paymentId the payment id
     * @return the payment
     */
    Payment findByPaymentIntentId(String paymentId);
}

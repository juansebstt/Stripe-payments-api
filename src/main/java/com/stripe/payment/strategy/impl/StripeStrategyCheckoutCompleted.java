package com.stripe.payment.strategy.impl;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.payment.common.entities.Payment;
import com.stripe.payment.common.enums.StripeEventEnum;
import com.stripe.payment.repositories.PaymentRepository;
import com.stripe.payment.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The type Stripe strategy checkout completed.
 */
@Component
public class StripeStrategyCheckoutCompleted implements StripeStrategy {

    private final PaymentRepository paymentRepository;

    /**
     * Instantiates a new Stripe strategy checkout completed.
     *
     * @param paymentRepository the payment repository
     */
    public StripeStrategyCheckoutCompleted(PaymentRepository paymentRepository) {

        this.paymentRepository = paymentRepository;

    }

    @Override
    public boolean isApplicable(Event event) {

        return StripeEventEnum.CHECKOUT_SESSION_COMPLETED.value.equals(event.getType());
    }

    @Override
    public Event process(Event event) {

        var session = this.deserialize(event);

        return Optional.of(event)
                .map(given -> paymentRepository.findByPaymentIntentId(session.getPaymentIntent()))
                .map(payment -> setProductId(payment, session.getMetadata().get("product_id")))
                .map(paymentRepository::save)
                .map(given -> event)
                .orElseThrow(() -> new RuntimeException("Process failed"));
    }

    private Payment setProductId(Payment payment, String productId) {

        payment.setProductId(productId);

        payment.setStripeEventEnum(StripeEventEnum.CHECKOUT_SESSION_COMPLETED);
        return payment;
    }

    private Session deserialize(Event event) {

        return (Session) event.getDataObjectDeserializer()
                .getObject()
                .orElseThrow(() -> new RuntimeException("Error Deserializing"));
    }
}
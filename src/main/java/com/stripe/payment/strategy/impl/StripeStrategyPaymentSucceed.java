package com.stripe.payment.strategy.impl;

import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.payment.common.entities.Payment;
import com.stripe.payment.common.enums.StripeEventEnum;
import com.stripe.payment.repositories.PaymentRepository;
import com.stripe.payment.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The type Stripe strategy payment succeed.
 */
@Component
public class StripeStrategyPaymentSucceed implements StripeStrategy {

    private final PaymentRepository paymentRepository;

    /**
     * Instantiates a new Stripe strategy payment succeed.
     *
     * @param paymentRepository the payment repository
     */
    public StripeStrategyPaymentSucceed(PaymentRepository paymentRepository) {

        this.paymentRepository = paymentRepository;
    }

    @Override
    public boolean isApplicable(Event event) {

        return StripeEventEnum.PAYMENT_INTENT_SUCCEEDED.value.equals(event.getType());
    }

    @Override
    public Event process(Event event) {

        return Optional.of(event)
                .map(this::deserialize)
                .map(this::mapToEntity)
                .map(paymentRepository::save)
                .map(given -> event)
                .orElseThrow(() -> new RuntimeException("Process Failed"));
    }

    private Payment mapToEntity(PaymentIntent paymentIntent) {

        return Payment.builder()
                .paymentIntentId(paymentIntent.getId())
                .customerId(paymentIntent.getCustomer())
                .amount(paymentIntent.getAmount())
                .currency(paymentIntent.getCurrency())
                .type(StripeEventEnum.PAYMENT_INTENT_SUCCEEDED)
                .build();
    }

    private PaymentIntent deserialize(Event event) {

        return (PaymentIntent) event.getDataObjectDeserializer().getObject()
                .orElseThrow(() -> new RuntimeException("Object cant be parsed"));
    }
}

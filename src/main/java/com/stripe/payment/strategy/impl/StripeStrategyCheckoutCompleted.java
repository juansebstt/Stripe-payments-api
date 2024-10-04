package com.stripe.payment.strategy.impl;

import com.stripe.model.Event;
import com.stripe.payment.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

@Component
public class StripeStrategyCheckoutCompleted implements StripeStrategy {
    @Override
    public boolean isApplicable(Event event) {
        return false;
    }

    @Override
    public Event process(Event event) {
        return null;
    }
}

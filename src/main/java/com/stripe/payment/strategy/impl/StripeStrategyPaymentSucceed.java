package com.stripe.payment.strategy.impl;

import com.stripe.model.Event;
import com.stripe.payment.common.enums.StripeEventEnum;
import com.stripe.payment.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

@Component
public class StripeStrategyPaymentSucceed implements StripeStrategy {
    @Override
    public boolean isApplicable(Event event) {
        return StripeEventEnum.PAYMENT_EVENT_SUCCEED.value.equals(event.getType());
    }

    @Override
    public Event process(Event event) {
        return null;
    }
}

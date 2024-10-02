package com.stripe.payment.common.enums;

public enum StripeEventEnum {
    PAYMENT_EVENT_SUCCEED("payment_intent.succeeded"),
    CHECKOUT_SESSION_COMPLETED("checkout.session.completed");

    private final String value;

    StripeEventEnum(String value) {
        this.value = value;
    }
}

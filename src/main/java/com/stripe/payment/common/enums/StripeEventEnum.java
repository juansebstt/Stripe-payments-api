package com.stripe.payment.common.enums;

/**
 * The enum Stripe event enum.
 */
public enum StripeEventEnum {
    /**
     * Payment intent succeeded stripe event enum.
     */
    PAYMENT_INTENT_SUCCEEDED("payment_intent.succeeded"),
    /**
     * Checkout session completed stripe event enum.
     */
    CHECKOUT_SESSION_COMPLETED("checkout.session.completed");

    /**
     * The Value.
     */
    public final String value;

    StripeEventEnum(String value) {

        this.value = value;

    }
}

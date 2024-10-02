package com.stripe.payment.services;

import org.w3c.dom.events.Event;

public interface StripeService {

    void manageWebhook(Event event);
    Event constructEvent(String payload, String stripeHeader);
}

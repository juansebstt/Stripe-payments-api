package com.stripe.payment.services.impl;

import com.stripe.payment.services.StripeService;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.Event;

@Service
public class StripeServiceImpl implements StripeService {
    @Override
    public void manageWebhook(Event event) {

    }

    @Override
    public Event constructEvent(String payload, String stripeHeader) {
        return null;
    }
}

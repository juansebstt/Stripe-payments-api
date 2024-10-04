package com.stripe.payment.services.impl;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.net.Webhook;
import com.stripe.model.Event;
import com.stripe.payment.services.StripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StripeServiceImpl implements StripeService {

    private final String endpointSecret;

    public StripeServiceImpl(@Value("${stripe.endpoint.secret}") String endpointSecret) {
        this.endpointSecret = endpointSecret;
    }

    @Override
    public void manageWebhook(Event event) {
        Optional.of(event)
                .map(this::processStrategy)

    }

    private Event processStrategy(Event event) {
    }

    @Override
    public Event constructEvent(String payload, String stripeHeader) {
        try {
            return Webhook.constructEvent(payload, stripeHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            throw new RuntimeException(e);
        }
    }
}

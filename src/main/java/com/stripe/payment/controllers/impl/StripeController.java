package com.stripe.payment.controllers.impl;

import com.stripe.payment.controllers.StripeApi;
import com.stripe.payment.services.StripeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeController implements StripeApi {

    private final StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @Override
    public ResponseEntity<Void> stripeWebhook(String payload, String stripeHeader) {
        var event = stripeService.constructEvent(payload, stripeHeader);
        stripeService.manageWebhook(event);

        return ResponseEntity.noContent().build();
    }
}

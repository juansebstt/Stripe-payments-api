package com.stripe.payment.controllers.impl;

import com.stripe.payment.common.dto.CheckoutRequest;
import com.stripe.payment.common.dto.CheckoutResponse;
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

    @Override
    public ResponseEntity<CheckoutResponse> createCheckout(CheckoutRequest checkoutRequest) {
        return ResponseEntity.ok(stripeService.createCheckout(checkoutRequest));
    }

}

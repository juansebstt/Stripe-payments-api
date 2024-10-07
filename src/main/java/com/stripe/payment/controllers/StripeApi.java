package com.stripe.payment.controllers;

import com.stripe.payment.common.constants.ApiPathConstants;
import com.stripe.payment.common.dto.CheckoutRequest;
import com.stripe.payment.common.dto.CheckoutResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.STRIPE_ROUTE)
public interface StripeApi {

    @PostMapping("/webhook")
    ResponseEntity<Void> stripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-signature") String stripeHeader);

    @PostMapping("/checkout")
    ResponseEntity<CheckoutResponse> createCheckout(@RequestBody @Valid CheckoutRequest checkoutRequest);

}

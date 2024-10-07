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


/**
 * The interface Stripe api.
 */
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.STRIPE_ROUTE)
public interface StripeApi {
    /**
     * Stripe webhook response entity.
     *
     * @param payload      the payload
     * @param stripeHeader the stripe header
     * @return the response entity
     */
    @PostMapping(value = "/webhook")
    ResponseEntity<Void> stripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String stripeHeader
    );

    /**
     * Create checkout response entity.
     *
     * @param checkoutRequest the checkout request
     * @return the response entity
     */
    @PostMapping(value = "checkout")
    ResponseEntity<CheckoutResponse> createCheckout(@RequestBody @Valid CheckoutRequest checkoutRequest);

}

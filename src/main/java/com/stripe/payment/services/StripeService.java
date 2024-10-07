package com.stripe.payment.services;

import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.payment.common.dto.CheckoutRequest;
import com.stripe.payment.common.dto.CheckoutResponse;;

/**
 * The interface Stripe service.
 */
public interface StripeService {
    /**
     * Manage webhook.
     *
     * @param event the event
     */
    void manageWebhook(Event event);

    /**
     * Construct event event.
     *
     * @param payload      the payload
     * @param stripeHeader the stripe header
     * @return the event
     */
    Event constructEvent(String payload, String stripeHeader);

    /**
     * Create customer customer.
     *
     * @param email the email
     * @return the customer
     */
    Customer createCustomer(String email);

    /**
     * Create product product.
     *
     * @param name the name
     * @return the product
     */
    Product createProduct(String name);

    /**
     * Create price price.
     *
     * @param productId the product id
     * @return the price
     */
    Price createPrice(String productId);

    /**
     * Create checkout checkout response.
     *
     * @param checkoutRequest the checkout request
     * @return the checkout response
     */
    CheckoutResponse createCheckout(CheckoutRequest checkoutRequest);
}

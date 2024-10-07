package com.stripe.payment.services.impl;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.model.Event;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.PriceListParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.payment.common.dto.CheckoutRequest;
import com.stripe.payment.common.dto.CheckoutResponse;
import com.stripe.payment.services.StripeService;
import com.stripe.payment.strategy.StripeStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * The type Stripe service.
 */
@Service
public class StripeServiceImpl implements StripeService {

    private final String endpointSecret;
    private final List<StripeStrategy> stripeStrategies;

    /**
     * Instantiates a new Stripe service.
     *
     * @param endpointSecret   the endpoint secret
     * @param stripeStrategies the stripe strategies
     * @param secretKey        the secret key
     */
    public StripeServiceImpl(
            @Value("${stripe.endpoint.secret}") String endpointSecret, List<StripeStrategy> stripeStrategies,
            @Value("${stripe.secret.key}") String secretKey) {

        Stripe.apiKey = secretKey;
        this.endpointSecret = endpointSecret;
        this.stripeStrategies = Collections.unmodifiableList(stripeStrategies);

    }

    @Override
    public void manageWebhook(Event event) {

        Optional.of(event)
                .map(this::processStrategy);
    }

    private Event processStrategy(Event given) {

        return stripeStrategies.stream()
                .filter(stripeStrategy -> stripeStrategy.isApplicable(given))
                .findFirst()
                .map(stripeStrategy -> {
                    System.out.println("dddd");
                    return stripeStrategy;
                })
                .map(stripeStrategy -> stripeStrategy.process(given))
                .orElseGet(Event::new);
    }

    @Override
    public Event constructEvent(String payload, String stripeHeader) {

        try {
            return Webhook.constructEvent(payload, stripeHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer createCustomer(String email) {

        var customerCreateParams = CustomerCreateParams.builder()
                .setEmail(email)
                .build();

        try {
            return Customer.create(customerCreateParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product createProduct(String name) {

        var productCreate = ProductCreateParams.builder()
                .setName(name)
                .setType(ProductCreateParams.Type.SERVICE)
                .build();

        try {
            return Product.create(productCreate);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Price createPrice(String productId) {

        var createPrice = PriceCreateParams.builder()
                .setCurrency("USD")
                .setProduct(productId)
                .setUnitAmount(10000L)
                .build();

        try {
            return Price.create(createPrice);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CheckoutResponse createCheckout(CheckoutRequest checkoutRequest) {

        var priceId = getPriceIdForProduct(checkoutRequest.getProductId());
        SessionCreateParams sessionCreateParams = getSessionCreateParams(checkoutRequest, priceId);

        try {
            return Optional.of(Session.create(sessionCreateParams))
                    .map(session -> CheckoutResponse.builder()
                            .urlPayment(session.getUrl())
                            .build()
                    )
                    .orElseThrow(() -> new RuntimeException("Error Creating checkout"));
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    private SessionCreateParams getSessionCreateParams(CheckoutRequest checkoutRequest, String priceId) {

        return SessionCreateParams.builder()
                .setCustomer(checkoutRequest.getCustomerId())
                .setSuccessUrl("http://localhost:8080")
                .setCancelUrl("http://localhost:8080")
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setPrice(priceId)
                        .setQuantity(1L)
                        .build()
                )
                .putExtraParam("metadata", extraMetadata(checkoutRequest.getProductId()))
                .build();
    }

    private Map<String, Object> extraMetadata(String productId) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("product_id", productId);
        return metadata;
    }

    private String getPriceIdForProduct(String productId) {
        List<Price> prices = null;

        try {
            prices = Price.list(PriceListParams.builder().setProduct(productId).build()).getData();
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        return prices.stream()
                .findFirst()
                .map(Price::getId)
                .orElseThrow(() -> new RuntimeException("Price Not found"));
    }
}
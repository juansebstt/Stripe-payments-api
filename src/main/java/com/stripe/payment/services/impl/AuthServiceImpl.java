package com.stripe.payment.services.impl;

import com.stripe.payment.common.dto.UserRequest;
import com.stripe.payment.common.entities.UserModel;
import com.stripe.payment.repositories.UserRepository;
import com.stripe.payment.services.AuthService;
import com.stripe.payment.services.StripeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Auth service.
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final StripeService stripeService;

    /**
     * Instantiates a new Auth service.
     *
     * @param userRepository the user repository
     * @param stripeService  the stripe service
     */
    public AuthServiceImpl(UserRepository userRepository, StripeService stripeService) {

        this.userRepository = userRepository;
        this.stripeService = stripeService;

    }

    @Override
    public String createUser(UserRequest userRequest) {

        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(this::setUserCustomerAndProduct)
                .map(userRepository::save)
                .map(UserModel::getCustomerId)
                .orElseThrow(() -> new RuntimeException("Error Creating User"));
    }

    private UserModel setUserCustomerAndProduct(UserModel userModel) {

        var customerCreated = stripeService.createCustomer(userModel.getEmail());
        var productCreated = stripeService.createProduct(userModel.getName());
        stripeService.createPrice(productCreated.getId());

        userModel.setCustomerId(customerCreated.getId());
        userModel.setProductId(productCreated.getId());

        return userModel;
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .build();
    }
}

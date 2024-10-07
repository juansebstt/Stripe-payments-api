package com.stripe.payment.controllers;

import com.stripe.payment.common.constants.ApiPathConstants;
import com.stripe.payment.common.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The interface Auth api.
 */
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {
    /**
     * Create user response entity.
     *
     * @param userRequest the user request
     * @return the response entity
     */
    @PostMapping
    ResponseEntity<String> createUser(@RequestBody @Valid UserRequest userRequest);

}

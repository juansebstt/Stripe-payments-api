package com.stripe.payment.services;

import com.stripe.payment.common.dto.UserRequest;


/**
 * The interface Auth service.
 */
public interface AuthService {
    /**
     * Create user string.
     *
     * @param userRequest the user request
     * @return the string
     */
    String createUser(UserRequest userRequest);

}

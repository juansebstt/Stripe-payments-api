package com.stripe.payment.services;

import com.stripe.payment.common.dto.UserRequest;


public interface AuthService {

    String createUser(UserRequest userRequest);

}


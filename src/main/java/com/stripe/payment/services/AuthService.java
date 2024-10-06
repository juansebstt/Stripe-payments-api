package com.stripe.payment.services;

import com.stripe.payment.common.dto.AuthResponseDto;
import com.stripe.payment.common.dto.UserRequest;


public interface AuthService {

    AuthResponseDto createUser(UserRequest userRequest);

}


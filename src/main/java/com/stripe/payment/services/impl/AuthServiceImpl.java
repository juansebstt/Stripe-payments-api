package com.stripe.payment.services.impl;

import com.stripe.payment.common.dto.AuthResponseDto;
import com.stripe.payment.common.dto.UserRequest;
import com.stripe.payment.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponseDto createUser(UserRequest userRequest) {
        return null;
    }
}

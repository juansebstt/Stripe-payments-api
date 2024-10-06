package com.stripe.payment.controllers.impl;


import com.stripe.payment.common.dto.AuthResponseDto;
import com.stripe.payment.common.dto.UserRequest;
import com.stripe.payment.controllers.AuthApi;
import com.stripe.payment.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponseDto> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }
}

package com.stripe.payment.controllers.impl;



import com.stripe.payment.common.dto.UserRequest;
import com.stripe.payment.controllers.AuthApi;
import com.stripe.payment.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;

    /**
     * Instantiates a new Auth controller.
     *
     * @param authService the auth service
     */
    public AuthController(AuthService authService) {

        this.authService = authService;

    }

    @Override
    public ResponseEntity<String> createUser(UserRequest userRequest) {

        return ResponseEntity.ok(authService.createUser(userRequest));

    }
}

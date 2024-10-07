package com.stripe.payment.controllers;

import com.stripe.payment.common.constants.ApiPathConstants;
import com.stripe.payment.common.dto.AuthResponseDto;
import com.stripe.payment.common.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {

    @PostMapping
    ResponseEntity<AuthResponseDto> createUser(@RequestBody @Valid UserRequest userRequest);

}

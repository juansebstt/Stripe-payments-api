package com.stripe.payment.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * The type User request.
 */
@Data
@Builder
public class UserRequest {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

}

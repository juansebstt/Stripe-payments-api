package com.stripe.payment.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutResponse {

    @NotNull
    private String customerId;

    @NotNull
    private String paymentId;

}

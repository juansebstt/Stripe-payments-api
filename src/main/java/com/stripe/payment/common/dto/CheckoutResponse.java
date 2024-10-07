package com.stripe.payment.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
public class CheckoutResponse {

    private String urlPayment;

}

package com.stripe.payment.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * The type Checkout response.
 */
@Data
@Builder
public class CheckoutResponse {

    private String urlPayment;

}

package com.stripe.payment.common.entities;

import com.stripe.payment.common.enums.StripeEventEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * The type Payment.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String paymentIntentId;
    private String customerId;
    private String productId;
    private Long amount;
    private String currency;

    @Enumerated(EnumType.STRING)
    private StripeEventEnum type;

}


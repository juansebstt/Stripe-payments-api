package com.stripe.payment.common.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private long id;
    private String paymentIntentId;
    private String customerId;
    private String productId;
    private Long amount;
    private String currency;
    private String type;
}

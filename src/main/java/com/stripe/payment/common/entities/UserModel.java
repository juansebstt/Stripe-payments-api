package com.stripe.payment.common.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * The type User model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String customerId;
    private String productId;

}

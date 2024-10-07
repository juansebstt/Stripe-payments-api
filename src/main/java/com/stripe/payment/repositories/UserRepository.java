package com.stripe.payment.repositories;

import com.stripe.payment.common.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {
}

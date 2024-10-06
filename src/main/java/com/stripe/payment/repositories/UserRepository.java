package com.stripe.payment.repositories;

import com.stripe.payment.common.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}

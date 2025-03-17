package com.dealer.carsdealer.repositories;

import com.dealer.carsdealer.models.Cart;
import com.dealer.carsdealer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
} 
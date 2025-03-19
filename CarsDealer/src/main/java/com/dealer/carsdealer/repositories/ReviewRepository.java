package com.dealer.carsdealer.repositories;
import java.util.List; 

import com.dealer.carsdealer.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCarId(int carId);
}

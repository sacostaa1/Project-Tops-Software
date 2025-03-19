package com.dealer.carsdealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dealer.carsdealer.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
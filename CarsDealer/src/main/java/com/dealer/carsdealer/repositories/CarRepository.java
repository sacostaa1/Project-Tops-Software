package com.dealer.carsdealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dealer.carsdealer.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
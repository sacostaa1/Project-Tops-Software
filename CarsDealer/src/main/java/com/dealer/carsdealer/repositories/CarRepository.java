package com.dealer.carsdealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dealer.carsdealer.models.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
    
    // Método para buscar por cilindraje, tipo de carro y precio
    List<Car> findByCylinderAndTypeCarAndPriceLessThanEqual(int cylinder, String typeCar, int price);

    // Método para buscar por cilindraje y precio
    List<Car> findByCylinderAndPriceLessThanEqual(int cylinder, int price);
    
    // Método para buscar por tipo de carro y precio
    List<Car> findByTypeCarAndPriceLessThanEqual(String typeCar, int price);
    
    // Método para buscar solo por precio
    List<Car> findByPriceLessThanEqual(int price);
}

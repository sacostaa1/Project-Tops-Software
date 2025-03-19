package com.dealer.carsdealer.controllers;

import com.dealer.carsdealer.models.Car;
import com.dealer.carsdealer.models.Review;
import com.dealer.carsdealer.repositories.CarRepository;
import com.dealer.carsdealer.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReviewController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/review-detail/{id}")
public String showCarDetails(@PathVariable("id") int id, Model model) {
        // Buscar el carro por su ID
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            model.addAttribute("car", car);
            model.addAttribute("reviews", car.getReviews());
        }
        return "car-detail"; // Asegúrate de que el nombre del template coincida
    }

    @PostMapping("/add-review/{carId}")
    public String addReview(@PathVariable int carId, String title, Integer rating, String comment) {
        Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            Review newReview = new Review(title, rating, comment, car);
            reviewRepository.save(newReview);
        }
        return "redirect:/car-detail/" + carId; // Redirigir a la página de detalles del carro
    }
}

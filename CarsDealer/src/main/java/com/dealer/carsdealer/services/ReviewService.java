package com.dealer.carsdealer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.carsdealer.models.Review;
import com.dealer.carsdealer.repositories.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Obtener todas las reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Obtener una review por su ID
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    // Guardar una review
    public Review saveReview(Review review) {
        try {
            return reviewRepository.save(review);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la review: " + e.getMessage());
        }
    }

    // Eliminar una review
    public void deleteReview(Long id) {
        try {
            if (!reviewRepository.existsById(id)) {
                throw new RuntimeException("No se encontró la review con ID: " + id);
            }
            reviewRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la review: " + e.getMessage());
        }
    }

    // Verificar si existe una review
    public boolean existsReview(Long id) {
        return reviewRepository.existsById(id);
    }

    // Actualizar una review
    public Review updateReview(Long id, Review reviewDetails) {
        try {
            Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la review con ID: " + id));
            
            review.setTitle(reviewDetails.getTitle());
            review.setRating(reviewDetails.getRating());
            review.setComment(reviewDetails.getComment());
            review.setCarModel(reviewDetails.getCarModel());
            
            return reviewRepository.save(review);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la review: " + e.getMessage());
        }
    }
}
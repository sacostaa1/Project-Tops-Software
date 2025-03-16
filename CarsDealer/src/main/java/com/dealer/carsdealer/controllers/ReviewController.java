package com.dealer.carsdealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.dealer.carsdealer.models.Review;
import com.dealer.carsdealer.services.ReviewService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Actividad 1 - Vista inicial
    @GetMapping("/")
    public String index() {
        return "home/index";  // Corregido para apuntar a /templates/home/index.html
    }

    // Actividad 2 - Formulario de creación
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("review", new Review());
        return "reviews/create";  // Apunta a /templates/reviews/create.html
    }

    // Actividad 3 - Inserción de objeto
    @PostMapping("/create")
    public String createReview(@Valid @ModelAttribute("review") Review review, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reviews/create";
        }
        
        reviewService.saveReview(review);
        model.addAttribute("message", "Elemento creado satisfactoriamente");
        return "reviews/success";  // Apunta a /templates/reviews/success.html
    }

    // Actividad 4 - Listar objetos
    @GetMapping("/list")
    public String listReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews/list";  // Apunta a /templates/reviews/list.html
    }

    // Actividad 5 - Ver un objeto
    @GetMapping("/{id}")
    public String showReview(@PathVariable Long id, Model model) {
        try {
            Review review = reviewService.getReviewById(id)
                .orElseThrow(() -> new RuntimeException("Review no encontrada"));
            model.addAttribute("review", review);
            return "reviews/show";  // Apunta a /templates/reviews/show.html
        } catch (RuntimeException e) {
            model.addAttribute("error", "Review no encontrada");
            return "redirect:/reviews/list";
        }
    }

    // Actividad 6 - Borrar objeto
    @PostMapping("/{id}/delete")
    public String deleteReview(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            reviewService.deleteReview(id);
            redirectAttributes.addFlashAttribute("message", "Review eliminada exitosamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la review: " + e.getMessage());
        }
        return "redirect:/reviews/list";
    }

    // Manejador de excepciones para toda la clase
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", "Ha ocurrido un error: " + e.getMessage());
        return "redirect:/reviews/list";
    }
}
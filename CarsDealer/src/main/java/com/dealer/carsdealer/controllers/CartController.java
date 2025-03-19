package com.dealer.carsdealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.dealer.carsdealer.CarsDealerApplication;
import com.dealer.carsdealer.models.Cart;
import com.dealer.carsdealer.models.User;
import com.dealer.carsdealer.repositories.CartRepository;
import com.dealer.carsdealer.services.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;

    private final CarsDealerApplication carsDealerApplication;

    @Autowired
    private CartService cartService;

    CartController(CarsDealerApplication carsDealerApplication, CartRepository cartRepository) {
        this.carsDealerApplication = carsDealerApplication;
        this.cartRepository = cartRepository;
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return (User) principal;
        } else {
            throw new RuntimeException("Usuario no autenticado");
        }
    }

    @GetMapping("")
    public String viewCart(Model model) {
        try {
            User currentUser = getCurrentUser();

            Cart cart = cartService.getCartByUser(currentUser).orElseGet(() -> cartService.createCart(currentUser));

            model.addAttribute("cart", cart);
            return "cart/view";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el carrito: " + e.getMessage());
            return "redirect:/";
        }
    }
}
package com.dealer.carsdealer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.carsdealer.models.Car;
import com.dealer.carsdealer.models.Cart;
import com.dealer.carsdealer.models.User;
import com.dealer.carsdealer.repositories.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Optional<Cart> getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public Cart createCart(User user) {
        Cart cart = new Cart(user);
        return cartRepository.save(cart);
    }

    public Cart addCarToCart(Cart cart, Car car) {
        cart.addCar(car);
        return cartRepository.save(cart);
    }

    public Cart removeCarFromCart(Cart cart, Car car) {
        cart.removeCar(car);
        return cartRepository.save(cart);
    }

    public Cart clearCart(Cart cart) {
        cart.clearCart();
        return cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public boolean existsCart(Long id) {
        return cartRepository.existsById(id);
    }
} 
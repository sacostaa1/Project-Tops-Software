package com.dealer.carsdealer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // Mostrar la página de inicio de sesión
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Esto se refiere a login.html
    }
}
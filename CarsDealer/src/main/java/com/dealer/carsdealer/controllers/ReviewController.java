package com.dealer.carsdealer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/reviews")
public class ReviewController {

    // Vista inicial
    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    // Formulario de creación de reviews
    @GetMapping("/create")
    public String createReview() {
        return new String();
    }
    
    

}

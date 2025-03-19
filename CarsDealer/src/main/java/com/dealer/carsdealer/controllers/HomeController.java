package com.dealer.carsdealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dealer.carsdealer.models.Contact;
import com.dealer.carsdealer.repositories.ContactRepository;

@Controller
public class HomeController {

    @Autowired
    private ContactRepository contactRepository;  

    // Mostrar el formulario
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contacto", new Contact("", ""));  // Inicializar un objeto Contacto vacío
        return "index";  
    }

    // Procesar el formulario de contacto y guardar en la base de datos
    @PostMapping("/enviarContacto")
    public String enviarFormulario(@ModelAttribute Contact contact, Model model) {
        contactRepository.save(contact);
        model.addAttribute("mensaje", "¡Gracias por contactarnos, " + contact.getNombre() + "!");
        return "index";
    }
}

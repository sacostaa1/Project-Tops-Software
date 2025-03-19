package com.dealer.carsdealer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dealer.carsdealer.models.Car;
import com.dealer.carsdealer.models.Review;
import com.dealer.carsdealer.repositories.CarRepository;
import com.dealer.carsdealer.repositories.ReviewRepository;

import jakarta.validation.Valid;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReviewRepository reviewRepository;
    
    @GetMapping("/cars/catalogo")
    public String showCatalogoPage(Model model) {
        List<Car> cars = carRepository.findAll();  
        model.addAttribute("cars", cars);
        return "cars/catalogo";  
    }

    @GetMapping("/car-detail/{id}")
    public String showCarDetail(@PathVariable int id, Model model) {
        // Buscar el carro por su ID
        Optional<Car> car = carRepository.findById(id);
    
        if (car.isPresent()) {
            // Obtener las reseñas del carro
            List<Review> reviews = reviewRepository.findByCarId(id);  // Consulta las reseñas por carId
            model.addAttribute("car", car.get());  // Agrega el carro al modelo
            model.addAttribute("reviews", reviews);  // Agrega las reseñas al modelo
            return "car-detail";  // Devuelve la vista de detalles del carro
        } else {
            model.addAttribute("errorMessage", "Carro no encontrado");
            return "car-detail"; // Mostrar un mensaje de error si no se encuentra el carro
        }
    }
    

    @PostMapping("/car-detail/{id}/add-review")
public String addReview(@PathVariable int id, @Valid @ModelAttribute("review") Review review, BindingResult result, Model model) {
    if (result.hasErrors()) {
        return "car-detail"; // Si hay errores, vuelve a mostrar la vista de detalles del carro
    }

    try {
        // Buscar el carro y asignárselo a la reseña
        Optional<Car> car = carRepository.findById(id);
        
            review.setCar(car.get()); // Asocia la reseña al carro
            reviewRepository.save(review); // Guarda la reseña
        

        
        return "redirect:/car-detail/" + id;
    } catch (Exception e) {
        model.addAttribute("errorMessage", "Hubo un error al guardar la reseña.");
        return "car-detail"; // Redirige nuevamente a la página de detalles del carro con el mensaje de error
    }
}

    


    @GetMapping("/car-add")
    public String addCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "car-add";
    }

    @GetMapping("/car-list")
    public String showCarList(Model model) {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "car-list";
    }

  

    @GetMapping("/car-delete/{id}")
    public String deleteCar(@PathVariable int id) {
        carRepository.deleteById(id);
        return "redirect:/car-list";
    }


    @PostMapping("/car/save")
    public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("car", car);
            return "car-add";
        }

        carRepository.save(car);

        model.addAttribute("message", "Elemento creado satisfactoriamente");
        return "car-success";
    }

}
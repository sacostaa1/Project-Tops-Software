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
import org.springframework.web.bind.annotation.RequestParam;

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
public String showCatalogoPage(@RequestParam(value = "cilindraje", defaultValue = "Todos") String cilindraje,
                               @RequestParam(value = "tipoCarro", defaultValue = "Todos") String tipoCarro,
                               @RequestParam(value = "precio", defaultValue = "10000") int precio,
                               Model model) {

    List<Car> cars;

    try {
        // Verificar si el cilindraje es "Todos"
        if (cilindraje.equals("Todos") && tipoCarro.equals("Todos") && precio == 10000) {
            // Si no hay filtro, mostramos todos los carros
            cars = carRepository.findAll();
        } 
        // Filtrar por cilindraje y tipo de carro si ambos son específicos
        else if (!cilindraje.equals("Todos") && !tipoCarro.equals("Todos")) {
            cars = carRepository.findByCylinderAndTypeCarAndPriceLessThanEqual(
                    Integer.parseInt(cilindraje), tipoCarro, precio);
        } 
        // Filtrar solo por cilindraje
        else if (!cilindraje.equals("Todos")) {
            cars = carRepository.findByCylinderAndPriceLessThanEqual(Integer.parseInt(cilindraje), precio);
        } 
        // Filtrar solo por tipo de carro
        else if (!tipoCarro.equals("Todos")) {
            cars = carRepository.findByTypeCarAndPriceLessThanEqual(tipoCarro, precio);
        } 
        // Filtrar solo por precio
        else {
            cars = carRepository.findByPriceLessThanEqual(precio);
        }

        model.addAttribute("cars", cars);
        return "cars/catalogo";  // Vista que muestra el catálogo filtrado

    } catch (Exception e) {
        // Imprimir el error en los logs
        e.printStackTrace();
        model.addAttribute("error", "Ocurrió un error al obtener los carros. Intenta de nuevo más tarde.");
        return "error";  // Asegúrate de tener una vista 'error.html'
    }
}


    @GetMapping("/car-detail/{id}")
    public String showCarDetail(@PathVariable int id, Model model) {
        Optional<Car> car = carRepository.findById(id);
    
        if (car.isPresent()) {
            List<Review> reviews = reviewRepository.findByCarId(id);  
            model.addAttribute("car", car.get());  
            model.addAttribute("reviews", reviews);  
            return "car-detail";  
        } else {
            model.addAttribute("errorMessage", "Carro no encontrado");
            return "car-detail"; 
        }
    }

    @PostMapping("/car-detail/{id}/add-review")
    public String addReview(@PathVariable("id") int id,
                            @RequestParam("title") String title,
                            @RequestParam("rating") int rating,
                            @RequestParam("comment") String comment,
                            Model model) {

        // Buscar el carro por su ID
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro no encontrado"));

        // Crear la nueva reseña
        Review review = new Review(title, rating, comment, car);

        // Guardar la reseña
        reviewRepository.save(review);

        
        return "redirect:/car-detail/" + id;
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
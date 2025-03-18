package com.dealer.carsdealer.controllers;

import com.dealer.carsdealer.models.Car;
import com.dealer.carsdealer.repositories.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/")
    public String showCarPage(Model model) {
        return "car";
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

    @GetMapping("/car-detail/{id}")
    public String showCarDetail(@PathVariable int id, Model model) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());
            return "car-detail";
        } else {
            return "redirect:/car-list";
        }
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
package com.dealer.carsdealer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String make_model;

    @ElementCollection
    @CollectionTable(name = "car_equipment", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "equipment_item")
    private List<String> equipment;

    @NotNull(message = "El precio no puede estar vacío")
    @Min(value = 1000, message = "El precio debe ser mayor a 1000")
    private int price;

    @NotNull(message = "La cilindrada no puede estar vacía")
    @Min(value = 1, message = "La cilindrada debe ser mayor a 0")
    private int cylinder;

    @NotNull
    private String color;

    @NotNull
    private String typeCar;

    @NotNull
    private String description;

    public Car() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake_model() {
        return make_model;
    }

    public void setMake_model(String make_model) {
        this.make_model = make_model;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

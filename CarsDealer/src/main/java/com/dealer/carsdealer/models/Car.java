package com.dealer.carsdealer.models;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

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

    private String imageUrl;

    @OneToMany(mappedBy = "car")
    private List<Review> reviews;

    

    public Car() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
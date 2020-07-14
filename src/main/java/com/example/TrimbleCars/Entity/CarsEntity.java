package com.example.TrimbleCars.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CARS")
public class CarsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;

    @Column(name="model")
    private String model;

    @Column(name="Details")
    private String Details;

    @Column(name="status")
    private String status;

    @Column(name="fuelType")
    private String fuelType;

    @Column(name="brand")
    private String brand;

    @Column(name="seatingCapacity")
    private String seatingCapacity;

    @Column(name="mileage")
    private int mileage;

    @Column(name="carNumber")
    private int carNumber;
}

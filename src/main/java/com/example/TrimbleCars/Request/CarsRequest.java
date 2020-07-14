package com.example.TrimbleCars.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CarsRequest {
    private String model;
    private String Details;
    private String status;
    private String fuelType;
    private String brand;
    private String seatingCapacity;
    private int mileage;
    // @NotNull(message = "carNumber must be specified")
    //@Size(min = 1, message = "carNumber must be specified")
    private int carNumber;

    public CarsRequest(String fabia, String briliant_silver_colour, String ideal, String diesel, String skoda, int i, int i1) {
    }
}

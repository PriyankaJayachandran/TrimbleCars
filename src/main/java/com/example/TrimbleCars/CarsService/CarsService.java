package com.example.TrimbleCars.CarsService;

import com.example.TrimbleCars.Entity.CarsEntity;
import com.example.TrimbleCars.Exception.CarNumberAlreadyEnrolledException;
import com.example.TrimbleCars.Exception.EntityNotFoundException;
import com.example.TrimbleCars.Repo.CarsRepository;
import com.example.TrimbleCars.Request.CarsRequest;
import com.example.TrimbleCars.Response.CarResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CarsService {

    @Autowired
    CarsRepository carsRepository;

    @Transactional
    public void enrollCar(CarsRequest request) {
        validateCarNumber(request.getCarNumber());
        log.info("Already enrolled carNumber validation for {} ", request.getCarNumber());
        CarsEntity carsEntity = CarsEntity.builder()
                .brand(request.getBrand())
                .carNumber(request.getCarNumber())
                .Details(request.getDetails())
                .fuelType(request.getFuelType())
                .mileage(request.getMileage())
                .model(request.getModel())
                .seatingCapacity(request.getSeatingCapacity())
                .status(request.getStatus())
                .build();
        carsRepository.save(carsEntity);
    }

    public Optional<CarResponse> getCarStatus(int carNumber) {
        log.info("Retrieve status and details for carNumber {} ", carNumber);
        Optional<CarsEntity> optionalCar = Optional.ofNullable(carsRepository.findBycarNumber(carNumber).orElseThrow(() -> new EntityNotFoundException("Car number does nto exist")));
        Optional<CarResponse> carResponse = Optional.of(CarResponse.builder().currentStatus(optionalCar.get().getStatus()).details(optionalCar.get().getDetails()).build());
        return carResponse;
    }

    protected void validateCarNumber(int CarNumber) {
        boolean alreadyEnrolled =
                Optional.of(carsRepository.findBycarNumber(CarNumber).isPresent()).orElse(false);
        if (alreadyEnrolled) {
            throw new CarNumberAlreadyEnrolledException("Car number already enrolled");
        }
    }
}


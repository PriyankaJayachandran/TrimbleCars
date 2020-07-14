package com.example.TrimbleCars.CarsController;

import com.example.TrimbleCars.CarsService.CarsService;
import com.example.TrimbleCars.Request.CarsRequest;
import com.example.TrimbleCars.Response.CarResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class CarsController {

    @Autowired
    CarsService carsService;

    @PostMapping(value = "/registerCar", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> enrollCar(
            @Valid @RequestBody CarsRequest request) {
        log.info(
                "Car enrollment request for {} ", request.getCarNumber());
        carsService.enrollCar(request);
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(
            value = "getCarStatus/{carNumber}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Optional<CarResponse>> getClientResponse(
            @PathVariable int carNumber) {
        log.info(
                "Get request to retrieve status and details of for carNumber {} ", carNumber);
        Optional<CarResponse> carResponse =
                carsService.getCarStatus(carNumber);
        return new ResponseEntity<>(carResponse, HttpStatus.OK);
    }

}

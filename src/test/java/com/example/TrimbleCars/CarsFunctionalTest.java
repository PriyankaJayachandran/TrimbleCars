package com.example.TrimbleCars;

import com.example.TrimbleCars.Request.CarsRequest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class CarsFunctionalTest {

    private static final String URL_PATH = "/registerCar";

    @Test
    public void testEnrollCar() {
        CarsRequest carsRequest = prepareCarsRequest();
        whenPost(carsRequest)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void testAlreadyEnrolledCar() {
        CarsRequest carsRequest = prepareCarsRequest();
        whenPost(carsRequest)
                .then()
                .log()
                .all();

        CarsRequest request = prepareCarsRequest();
        request.setCarNumber(1234);
        Response response = whenPost(request);
        response.then().log().all().statusCode(HttpStatus.SC_BAD_REQUEST);
        assertThat(response.getBody().jsonPath().get("errorMessage").toString())
                .isEqualTo("Car number already enrolled");
    }

    public CarsRequest prepareCarsRequest() {
        return CarsRequest.builder()
                .brand("skoda")
                .carNumber(1234)
                .Details("Briliant silver colour")
                .fuelType("diesel")
                .mileage(25)
                .model("fabia")
                .seatingCapacity("5")
                .status("ideal")
                .build();
    }

    private Response whenPost(CarsRequest request) {
        return given()
                .contentType(APPLICATION_JSON.getMimeType())
                .body(request)
                .log()
                .all()
                .when()
                .post(URL_PATH);
    }

}


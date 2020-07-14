
package com.example.TrimbleCars;

import com.example.TrimbleCars.CarsService.CarsService;
import com.example.TrimbleCars.Request.CarsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@WebMvcTest
@WebAppConfiguration
public class CarsTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    CarsService carsService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void enrollNewCarTest() throws Exception {
        CarsRequest carsRequest = CarsRequest.builder()
                .brand("skoda")
                .carNumber(1234)
                .Details("Briliant silver colour")
                .fuelType("diesel")
                .mileage(25)
                .model("fabia")
                .seatingCapacity("5")
                .status("ideal")
                .build();

        Mockito.doNothing().when(carsService).enrollCar(carsRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/registerCar")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(carsRequest))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    public static String asJsonString(final CarsRequest carsRequest) {
        try {
            return new ObjectMapper().writeValueAsString(carsRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

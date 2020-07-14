package com.example.TrimbleCars.CarsController;

import com.example.TrimbleCars.ErrorHandler.ErrorResponse;
import com.example.TrimbleCars.Exception.CarNumberAlreadyEnrolledException;
import com.example.TrimbleCars.Exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice(assignableTypes = {CarsController.class})
@ResponseBody
public class CarsControllerErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CarNumberAlreadyEnrolledException.class
    })
    public ErrorResponse handleCarsValidationException(Exception ex) {
        log.error("CarNumberAlreadyEnrolledException occurred", ex);
        return createCarException(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class
    })
    public ErrorResponse handleCarNotExistException(Exception ex) {
        log.error("Car Number Not Exist Exception occurred", ex);
        return createCarException(ex);
    }

    protected ErrorResponse createCarException(Exception ex) {
        return ErrorResponse.builder()
                .errorCode(400)
                .errorMessage(ex.getMessage())
                .build();
    }
}

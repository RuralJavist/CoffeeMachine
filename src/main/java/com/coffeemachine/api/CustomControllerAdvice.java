package com.coffeemachine.api;

import com.coffeemachine.dto.exceptions.SimpleExceptionResponseDto;
import com.coffeemachine.dto.exceptions.ValidationExceptionDto;
import com.coffeemachine.dto.exceptions.ValidationResponseDto;
import com.coffeemachine.exceptions.DrinkAddingException;
import com.coffeemachine.exceptions.IngredientTankCapacityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationResponseDto validationExceptionHandler(MethodArgumentNotValidException exception, HttpServletRequest request){
        List<ValidationExceptionDto> exceptionDto = new ArrayList<>();
        exception.getAllErrors().forEach(objectError -> {
            ValidationExceptionDto validationExceptionDto = new ValidationExceptionDto(String.format("Exception throw in class - %s", objectError.getObjectName()),
                    objectError.getDefaultMessage(), objectError.getClass().getName());
            exceptionDto.add(validationExceptionDto);
        });
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        return new ValidationResponseDto(request.getServletPath(), status.name(), status.value(), exceptionDto);
    }

    @ExceptionHandler(value = IngredientTankCapacityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public SimpleExceptionResponseDto ingredientTankCapacityExceptionHandler(IngredientTankCapacityException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        return new SimpleExceptionResponseDto(request.getServletPath(), status.name(), status.value(), exception.getMessage());
    }

    @ExceptionHandler(value = DrinkAddingException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public SimpleExceptionResponseDto drinkAddingExceptionHandler(IngredientTankCapacityException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        return new SimpleExceptionResponseDto(request.getServletPath(), status.name(), status.value(), exception.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleExceptionResponseDto basicRuntimeExceptionHandler(RuntimeException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new SimpleExceptionResponseDto(request.getServletPath(), status.name(), status.value(), exception.getMessage());
    }

}

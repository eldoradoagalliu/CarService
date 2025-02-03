package com.carservice.service;

import com.carservice.model.Car;
import com.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public void addCar(Car car) {
        carRepository.save(car);
    }

    public Car findCar(String plate) {
        return carRepository.findByPlate(plate);
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }
}
package com.carservice.services;

import com.carservice.models.Car;
import com.carservice.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepo;

    public CarService(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    public void addCar(Car car) {
        carRepo.save(car);
    }

    public Car findCar(String plate) {
        return carRepo.findByPlate(plate);
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }
}
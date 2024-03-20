package com.carservice.controllers;

import com.carservice.models.Car;
import com.carservice.models.RepairService;
import com.carservice.services.CarService;
import com.carservice.services.RepairServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class MainController {
    @Autowired
    private CarService carService;

    @Autowired
    private RepairServicesService repairService;

    @GetMapping("/")
    public String home(@ModelAttribute("car") Car car, Model model) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("showAddCarForm", true);
        return "home";
    }

    @PostMapping("/searchCar")
    public String searchCar(@RequestParam("inputtedPlate") String inputtedPlate, @ModelAttribute("car") Car car, Model model) {
        Car foundCar = carService.findCar(inputtedPlate);
        String carPlate = foundCar != null ? foundCar.getPlate() : null;

        model.addAttribute("searchEnabled", true);
        model.addAttribute("carPlate", carPlate);
        model.addAttribute("cars", null);
        return "home";
    }

    @PostMapping("/addCar")
    public String addCar(@Valid @ModelAttribute("car") Car car, BindingResult result, Model model) {
        boolean carExists = Objects.nonNull(carService.findCar(car.getPlate()));

        if (result.hasErrors() || carExists) {
            final String CAR_EXISTS_MESSAGE = "This plate was added to the service before!";
            if (carExists) model.addAttribute("carExistsMessage", CAR_EXISTS_MESSAGE);
            model.addAttribute("cars", carService.getAllCars());
            model.addAttribute("showAddCarForm", true);
            return "home";
        }

        Car newCar = new Car(car.getPlate(), car.getBrand(), car.getCarRepairServices());
        carService.addCar(newCar);
        return "redirect:/car/" + car.getPlate();
    }

    @GetMapping("/showAllCars")
    public String showAllCars(@ModelAttribute("car") Car car, Model model) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("showAllCars", true);
        return "home";
    }

    @GetMapping("/car/{plate}")
    public String carDetails(@PathVariable("plate") String plate, @ModelAttribute("service") RepairService service, Model model) {
        Car car = carService.findCar(plate);
        model.addAttribute("car", car);
        return "car_details";
    }

    @PostMapping("/addRepairService")
    public String addRepairService(@RequestParam("plate") String plate, @Valid @ModelAttribute("service") RepairService service,
                                   BindingResult result, Model model) {
        Car car = carService.findCar(plate);

        if (result.hasErrors()) {
            model.addAttribute("car", car);
            return "car_details";
        }

        RepairService newService = new RepairService(service.getType(), service.getMileage(), car);
        repairService.addService(newService);
        car.getCarRepairServices().add(newService);
        return "redirect:/car/" + car.getPlate();
    }
}

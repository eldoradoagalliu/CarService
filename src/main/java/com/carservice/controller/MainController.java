package com.carservice.controller;

import com.carservice.model.Car;
import com.carservice.model.RepairService;
import com.carservice.service.CarService;
import com.carservice.service.RepairServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class MainController {

    private final CarService carService;
    private final RepairServicesService repairService;

    @GetMapping("/my/service")
    public String home(@ModelAttribute("car") Car car, Model model) {
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("showAddCarForm", true);
        return "home";
    }

    @PostMapping("/search")
    public String searchCar(@RequestParam("inputtedPlate") String inputtedPlate, @ModelAttribute("car") Car car, Model model) {
        Car foundCar = carService.findCar(inputtedPlate);
        String carPlate = foundCar != null ? foundCar.getPlate() : null;

        model.addAttribute("searchEnabled", true);
        model.addAttribute("carPlate", carPlate);
        model.addAttribute("cars", List.of());
        return "home";
    }

    @PostMapping
    public String addCar(@Valid @ModelAttribute("car") Car car, BindingResult result, Model model) {
        boolean carExists = Objects.nonNull(carService.findCar(car.getPlate()));

        if (result.hasErrors() || carExists) {
            final String CAR_EXISTS_MESSAGE = "This plate was added to the service before!";
            if (carExists) model.addAttribute("carExistsMessage", CAR_EXISTS_MESSAGE);
            model.addAttribute("cars", carService.getCars());
            model.addAttribute("showAddCarForm", true);
            return "home";
        }

        Car newCar = Car.builder()
                .plate(car.getPlate())
                .brand(car.getBrand())
                .carRepairServices(car.getCarRepairServices())
                .build();
        carService.addCar(newCar);
        return "redirect:/car/" + car.getPlate();
    }

    @GetMapping("/show/all")
    public String showAllCars(@ModelAttribute("car") Car car, Model model) {
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("showAllCars", true);
        return "home";
    }

    @GetMapping("/{plate}")
    public String carDetails(@PathVariable("plate") String plate, @ModelAttribute("service") RepairService service, Model model) {
        Car car = carService.findCar(plate);
        model.addAttribute("car", car);
        return "car_details";
    }

    @PostMapping("/repair/service")
    public String addRepairService(@RequestParam("plate") String plate, @Valid @ModelAttribute("service") RepairService service,
                                   BindingResult result, Model model) {
        Car car = carService.findCar(plate);

        if (result.hasErrors()) {
            model.addAttribute("car", car);
            return "car_details";
        }

        RepairService newService = RepairService.builder()
                .type(service.getType())
                .mileage(service.getMileage())
                .car(car)
                .build();
        repairService.addService(newService);
        car.getCarRepairServices().add(newService);
        return "redirect:/car/" + car.getPlate();
    }
}

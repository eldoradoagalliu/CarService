package com.carservice.services;

import com.carservice.models.RepairService;
import com.carservice.repositories.RepairServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class RepairServicesService {
    private final RepairServiceRepository repairServiceRepo;

    public RepairServicesService(RepairServiceRepository repairServiceRepo) {
        this.repairServiceRepo = repairServiceRepo;
    }

    public void addService(RepairService service) {
        repairServiceRepo.save(service);
    }
}

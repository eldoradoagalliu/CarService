package com.carservice.service;

import com.carservice.model.RepairService;
import com.carservice.repository.RepairServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepairServicesService {

    private final RepairServiceRepository repairServiceRepository;

    public void addService(RepairService service) {
        repairServiceRepository.save(service);
    }
}

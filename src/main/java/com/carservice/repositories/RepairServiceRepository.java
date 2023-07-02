package com.carservice.repositories;

import com.carservice.models.RepairService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairServiceRepository extends JpaRepository<RepairService, Long> {
}

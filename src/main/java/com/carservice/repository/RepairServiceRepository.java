package com.carservice.repository;

import com.carservice.model.RepairService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairServiceRepository extends JpaRepository<RepairService, Long> {
}

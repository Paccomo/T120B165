package com.benpus.SRs.repositories;

import com.benpus.SRs.models.Company;
import com.benpus.SRs.models.ShootingRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShootingRangeRepository extends JpaRepository<ShootingRange, Integer> {
    List<ShootingRange> findByFkCompany(Company fkCompany);
}

package com.benpus.srs.repositories;

import com.benpus.srs.models.Company;
import com.benpus.srs.models.ShootingRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShootingRangeRepository extends JpaRepository<ShootingRange, Integer> {
    List<ShootingRange> findByFkCompany(Company fkCompany);
}

package com.benpus.SRs.repositories;

import com.benpus.SRs.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}

package com.benpus.SRs.repositories;

import com.benpus.SRs.models.BusinessHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Integer> {

}

package com.benpus.srs.repositories;

import com.benpus.srs.models.FirearmReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirearmReservationRepository extends JpaRepository<FirearmReservation, Integer> {
}

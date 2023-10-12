package com.benpus.SRs.repositories;

import com.benpus.SRs.models.Firearm;
import com.benpus.SRs.models.ShootingRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FirearmRepository extends JpaRepository<Firearm, Integer> {
//    @Query("SELECT t FROM Firearms t WHERE t.fk_shootingRange = ?1")
    List<Firearm> findByfkShootingRange(ShootingRange fkShootingRange);
}

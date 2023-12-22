package com.benpus.srs.repositories;

import com.benpus.srs.models.Firearm;
import com.benpus.srs.models.ShootingRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FirearmRepository extends JpaRepository<Firearm, Integer> {
//    @Query("SELECT t FROM Firearms t WHERE t.fk_shootingRange = ?1")
    List<Firearm> findByfkShootingRange(ShootingRange fkShootingRange);
}

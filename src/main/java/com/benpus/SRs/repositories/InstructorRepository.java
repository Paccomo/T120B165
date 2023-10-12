package com.benpus.SRs.repositories;

import com.benpus.SRs.models.Firearm;
import com.benpus.SRs.models.Instructor;
import com.benpus.SRs.models.ShootingRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    List<Instructor> findByfkShootingRange(ShootingRange fkShootingRange);
}

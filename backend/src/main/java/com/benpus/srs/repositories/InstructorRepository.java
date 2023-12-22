package com.benpus.srs.repositories;

import com.benpus.srs.models.Instructor;
import com.benpus.srs.models.ShootingRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    List<Instructor> findByfkShootingRange(ShootingRange fkShootingRange);
}

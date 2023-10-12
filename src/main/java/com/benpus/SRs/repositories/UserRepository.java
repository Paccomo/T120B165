package com.benpus.SRs.repositories;

import com.benpus.SRs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

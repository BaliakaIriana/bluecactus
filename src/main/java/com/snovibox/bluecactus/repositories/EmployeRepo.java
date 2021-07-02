package com.snovibox.bluecactus.repositories;

import com.snovibox.bluecactus.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepo extends JpaRepository<Employe, Integer> {
}

package com.snovibox.bluecactus.services;

import com.snovibox.bluecactus.entities.Employe;
import com.snovibox.bluecactus.repositories.EmployeRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@AllArgsConstructor
public class EmployeService {
    private final EmployeRepo employeRepo;

    /**
     *
     * @param page [1,n]
     * @param limit
     * @return
     */
    public Page<Employe> getEmployes(int page, int limit) {
        return employeRepo.findAll(PageRequest.of(page - 1, limit));
    }

    public Employe getEmployeById(Integer id) throws EntityNotFoundException {
        return employeRepo.findById(id).orElseThrow(() -> {throw new EntityNotFoundException();});
    }
}

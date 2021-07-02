package com.snovibox.bluecactus.controllers;

import com.snovibox.bluecactus.dto.EmployeDTO;
import com.snovibox.bluecactus.dto.EmployesDTO;
import com.snovibox.bluecactus.dto.mappers.ToDtoMapper;
import com.snovibox.bluecactus.entities.Employe;
import com.snovibox.bluecactus.services.EmployeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employes")
@CrossOrigin
@AllArgsConstructor
public class EmployeController {

    private final EmployeService employeService;
    private final ToDtoMapper mapper;

    @GetMapping("")
    public ResponseEntity<EmployesDTO> endpointListeEmployes(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "15") Integer limit) {
        Page<Employe> employes = employeService.getEmployes(page, limit);
        EmployesDTO employesDTO = mapper.toEmployesDTO(employes);
        return ResponseEntity.ok(employesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeDTO> endpointSingleEmploye(@PathVariable Integer id) {
        Employe employe = employeService.getEmployeById(id);
        return ResponseEntity.ok(mapper.toEmployeDTO(employe));
    }
}

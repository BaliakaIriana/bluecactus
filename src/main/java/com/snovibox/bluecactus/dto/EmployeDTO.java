package com.snovibox.bluecactus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeDTO {

    private Integer id;
    private String nom;
    private String prenom;

    private Integer age;

    private String poste;
    private List<ExperienceDTO> experience;
}

package com.snovibox.bluecactus.dto;

import com.snovibox.bluecactus.entities.Employe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployesDTO {
    private Collection<Employe> employes;
    private Integer totalPages;
    private Long totalElements;
}

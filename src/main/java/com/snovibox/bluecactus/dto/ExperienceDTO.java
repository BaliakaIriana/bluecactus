package com.snovibox.bluecactus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO {
    private Integer id;
    private String titre;
    private String description;
}

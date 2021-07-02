package com.snovibox.bluecactus.dto.mappers;

import com.snovibox.bluecactus.dto.EmployeDTO;
import com.snovibox.bluecactus.dto.EmployesDTO;
import com.snovibox.bluecactus.dto.ExperienceDTO;
import com.snovibox.bluecactus.dto.UserDTO;
import com.snovibox.bluecactus.entities.Employe;
import com.snovibox.bluecactus.entities.Experience;
import com.snovibox.bluecactus.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class ToDtoMapper {

    public UserDTO toUserDTO(User u) {
        return new UserDTO(u.getId(), u.getUsername());
    }

    public EmployesDTO toEmployesDTO(Page<Employe> employes) {
        return new EmployesDTO(employes.toList(), employes.getTotalPages(), employes.getTotalElements());
    }

    public EmployeDTO toEmployeDTO(Employe employe) {
        List<Experience> experience = employe.getExperience();
        List<ExperienceDTO> experienceDTOS = new LinkedList<>();
        for (Experience exp : experience) {
            experienceDTOS.add(new ExperienceDTO(exp.getId(), exp.getTitre(), exp.getDescription()));
        }
        return new EmployeDTO(employe.getId(), employe.getNom(), employe.getPrenom(), employe.getAge(), employe.getPoste(), experienceDTOS);
    }
}

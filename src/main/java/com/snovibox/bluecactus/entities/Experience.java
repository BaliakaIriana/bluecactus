package com.snovibox.bluecactus.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experience_id_seq")
    @SequenceGenerator(name = "experience_id_seq", sequenceName = "experience_id_seq", initialValue = 10)
    private Integer id;

    private String titre;
    private String description;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    @JsonBackReference
    private Employe employe;
}

package com.snovibox.bluecactus.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_id_seq")
    @SequenceGenerator(name = "employe_id_seq", sequenceName = "employe_id_seq", initialValue = 10)
    private Integer id;

    @Column(length = 50)
    private String nom;
    @Column(length = 100)
    private String prenom;

    private Integer age;

    private String poste;

    @OneToMany(mappedBy = "employe")
    @JsonManagedReference
    private List<Experience> experience;
}

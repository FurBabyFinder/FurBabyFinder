package com.capstone.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @Column
    private Boolean vaccination;


    @ManyToOne
    @JoinTable (name = "pet_medical")
    private Pet pet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicalRecord")
    private List<MedicalImage> medicalImages;

}
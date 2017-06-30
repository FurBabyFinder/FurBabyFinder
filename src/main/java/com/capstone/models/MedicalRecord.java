package com.capstone.models;

import javax.persistence.*;

@Entity
@Table(name="medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @Column
    private Boolean vacination;


    @ManyToOne
    @JoinTable (name = "pet_id")
    private Pet pet;

}
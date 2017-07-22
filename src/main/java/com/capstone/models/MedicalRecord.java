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
    private String title;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String description;

    @Column
    private Boolean vaccination;


    @ManyToOne
    @JoinTable (name = "pet_medical")
    private Pet pet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicalRecord")
    private List<MedicalImage> medicalImages;

    public MedicalRecord(String description, String date, Boolean vaccination, Pet pet) {
        this.description = description;
        this.date = date;
        this.vaccination = vaccination;
        this.pet = pet;
    }
   public MedicalRecord(String description, Boolean vaccination, Pet pet, List<MedicalImage> medicalImages, String title) {
        this.description = description;
        this.vaccination = vaccination;
        this.pet = pet;
        this.medicalImages = medicalImages;
        this.title = title;
    }

    public MedicalRecord() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getVaccination() {
        return vaccination;
    }

    public void setVaccination(Boolean vaccination) {
        this.vaccination = vaccination;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<MedicalImage> getMedicalImages() {
        return medicalImages;
    }

    public void setMedicalImages(List<MedicalImage> medicalImages) {
        this.medicalImages = medicalImages;
    }
}
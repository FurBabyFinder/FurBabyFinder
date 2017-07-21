package com.capstone.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by melodytempleton on 7/21/17.
 */


@Entity
@Table(name="medical_images")
public class MedicalImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 500)
    @NotBlank(message = "Image must have a url")
    private String imageUrl;


    @ManyToOne
    @JoinTable(name = "record_to_image")
    private MedicalRecord medicalRecord;


    public MedicalImage(long id, String imageUrl, MedicalRecord medicalRecord) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.medicalRecord = medicalRecord;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}

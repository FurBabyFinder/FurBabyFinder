package com.capstone.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="pet_images")
public class PetImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    @NotBlank(message = "Image must have a url")
    private String ImageUrl;

    @Column(nullable = false, length = 45)
    @NotBlank(message = "Image must have a description")
    private String ImageDescription;

    @Column
    private boolean profilePic;

    @Column
    private boolean after_adoption

    @ManyToOne
    @JoinTable (name = "pet_id")
    private Pet pet;


}

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

    @Column(nullable = false, length = 500)
    @NotBlank(message = "Image must have a url")
    private String imageUrl;

    @Column
//            (nullable = false, length = 45)
//    @NotBlank(message = "Image must have a description")
    private String imageDescription;

    @Column
    private boolean profilePic;

    @Column
    private boolean afterAdoption = false;

    @ManyToOne
    @JoinTable (name = "pet_to_image")
    private Pet pet;

    public PetImage(String imageUrl, String imageDescription, Pet pet) {
        this.imageUrl = imageUrl;
        this.imageDescription = imageDescription;
        this.pet = pet;
    }

    public PetImage (Pet pet){
        this.pet = pet;
    }

    public PetImage(){}

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

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public boolean isProfilePic() {
        return profilePic;
    }

    public void setProfilePic(boolean profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isAfterAdoption() {
        return afterAdoption;
    }

    public void setAfterAdoption(boolean afterAdoption) {
        this.afterAdoption = afterAdoption;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}

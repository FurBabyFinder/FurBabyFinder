package com.capstone.svcs;

import com.capstone.models.Pet;
import com.capstone.models.PetImage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodytempleton on 7/9/17.
 */
public class PetDTO implements Serializable{

    private Pet pet;

    private List<PetImage> imageList;

    public PetDTO(Pet pet, List<PetImage> imageList) {
        this.pet = pet;
        this.imageList = imageList;
    }

    public PetDTO() {
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<PetImage> getImageList() {
        return this.imageList;
    }

    public void setImageList(List<PetImage> imageList) {
        this.imageList = imageList;
    }
}
